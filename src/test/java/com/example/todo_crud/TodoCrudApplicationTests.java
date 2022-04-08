package com.example.todo_crud;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.todo_crud.exception.ApiRequestException;
import com.example.todo_crud.models.TodoModel;
import com.example.todo_crud.models.TodoStatusE;
import com.example.todo_crud.repositories.TodoRepository;
import com.example.todo_crud.services.TodoService;
import com.example.todo_crud.utils.TodoState;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
class TodoCrudApplicationTests {

	@Mock
	TodoRepository repository;

	@Mock
	TodoState todoState;

	@InjectMocks
	TodoService service;

	@BeforeEach
	public void setUp() {
		//TodoModel todo = new TodoModel(1,"A", "a", "2022-01-14T15:16:24Z", "DONE");
		//when(repository.findById(1)).thenReturn(Optional.of(todo));
	}

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Obtener un todo")
	void testObtenerTodo() {

		int id = 1;
		TodoModel todoMock = new TodoModel(id, "A", "a", "2022-01-14T15:16:24Z", "DONE");
		when(repository.findById(id)).thenReturn(Optional.of(todoMock));

		TodoModel todo = service.getTodobyId(id);

		assertNotNull(todo);
		assertEquals(id, todo.getId());

		verify(repository).findById(id);
	}

	@Test()
	@DisplayName("Obtener lista de todos")
	void testObtenerListaTodos() {

		when(repository.findAll()).thenReturn(DatosTodo.TODOS);

		int sizeTodo = DatosTodo.TODOS.size();
		List<TodoModel> todos = service.getTodos();

		assertNotNull(todos);
		assertEquals(sizeTodo, todos.size());

		verify(repository).findAll();
	}

	@Test
	@DisplayName("Guardar un todo")
	void testGuardarTodo() {
		// Given
		TodoModel todoMock = new TodoModel(0, "A", "a", "2022-01-14T15:16:24Z", "DONE");

		when(repository.save(any(TodoModel.class))).then(new Answer<TodoModel>() {

			int secuencia = 1;

			@Override
			public TodoModel answer(InvocationOnMock invocation) throws Throwable {
				TodoModel todo = invocation.getArgument(0);
				todo.setId(secuencia++);
				return todo;
			}
		});

		// When
		TodoModel todoInserted = service.insertTodo(todoMock);
		
		// Then
		assertNotNull(todoInserted);
		assertNotEquals(0, todoInserted.getId());
		assertEquals(1, todoInserted.getId());

		//verify
		verify(repository).save(any(TodoModel.class));
	}

	@ParameterizedTest(name = "numero {index} tiene el valor {0} - {argumentsWithNames}")
	@MethodSource("generarVariosTodos")
	@DisplayName("Guardar varios todos")
	void testGuardarVariosTodos(TodoModel todoToInsert) {
		// Given
		int idExpected = todoToInsert.getId();

		when(repository.save(any(TodoModel.class))).then(new Answer<TodoModel>() {

			int secuencia = idExpected;

			@Override
			public TodoModel answer(InvocationOnMock invocation) throws Throwable {
				TodoModel todo = invocation.getArgument(0);
				todo.setId(secuencia);
				return todo;
			}
		});

		// When
		TodoModel todoInserted = service.insertTodo(todoToInsert);

		// Then
		assertNotNull(todoInserted);
		assertNotEquals(0, todoInserted.getId());
		assertEquals(idExpected, todoInserted.getId());

		// verify
		verify(repository).save(any(TodoModel.class));
	}

	static private List<TodoModel> generarVariosTodos() {
		return  Arrays.asList(
				new TodoModel(1, "A", "a", "2022-01-14T15:16:24Z", "DONE"),
				new TodoModel(2, "B", "b", "2022-01-14T15:16:24Z", "DONE"),
				new TodoModel(3, "C", "c", "2022-01-14T15:16:24Z", "DONE"));
	}

	@ParameterizedTest(name = "Elemento [{index}] tiene el valor {0} - {argumentsWithNames}")
	@ValueSource(ints = { 1, 2, 3, 4 })
	@DisplayName("Guardar varios todos verificando el auto increment")
	void testGuardarVariosTodosv2(int value) {
		// Given
		TodoModel todoMock = new TodoModel(0, "A", "a", "2022-01-14T15:16:24Z", "DONE");

		when(repository.save(any(TodoModel.class))).then(new Answer<TodoModel>() {

			int secuencia = value;

			@Override
			public TodoModel answer(InvocationOnMock invocation) throws Throwable {
				TodoModel todo = invocation.getArgument(0);
				todo.setId(secuencia);
				return todo;
			}
		});

		// When
		TodoModel todoInserted = service.insertTodo(todoMock);

		// Then
		assertNotNull(todoInserted);
		assertNotEquals(0, todoInserted.getId());
		assertEquals(value, todoInserted.getId());

		// verify
		verify(repository).save(any(TodoModel.class));
	}

	@Test
	@DisplayName("Todo not found")
	void testTodoNotFound() {
		// Given
		// TodoModel todo = null;
		// Optional<TodoModel> optTodo = Optional.ofNullable(todo);
		// when(repository.findById(any(Integer.class))).thenReturn(optTodo);
		when(repository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(null));

		// When
		Exception exception = assertThrows(ApiRequestException.class, () -> {
			service.getTodobyId(1);
		});

		ApiRequestException aex = (ApiRequestException) exception;

		//Then
		String msjEsperado = "Not found";
		HttpStatus statusEsperado = HttpStatus.NOT_FOUND;
		assertAll(
			() -> {
				assertEquals(aex.getMessage(), msjEsperado);
			},
			() -> {
				assertEquals(aex.getHttpStatus(), statusEsperado);
			}
		);
	}

	@Test
	@DisplayName("Update todo correctly")
	void testUpdateTodo() {
		// Given
		int id = 1;

		TodoModel todoBefore = new TodoModel(id, "A", "a", "2022-01-14T15:16:24Z", "TO_DO");
		TodoModel todoAfter  = new TodoModel(id, "A", "a", "2022-01-14T15:16:24Z", "IN_PROGRESS");
		
		when(repository.findById(id)).thenReturn(Optional.of(todoBefore));
		when(repository.save(todoAfter)).thenReturn(todoAfter);

		doNothing().when(todoState).setTodoModel(any(TodoModel.class));
		when(todoState.changeSimple(any(TodoStatusE.class))).thenReturn(true);
		
		// When
		TodoModel todoUpdated = service.updateTodo(todoAfter);


		// Then
		assertEquals(todoUpdated.getId(), todoBefore.getId());
		assertNotEquals(todoAfter.getStatus(), todoBefore.getStatus());
		assertEquals(todoUpdated.getStatus(), todoBefore.getStatus());


		//Verify
		verify(repository).findById(id);
		verify(repository).save(any(TodoModel.class));
	}

}
