<script lang="ts">
	interface Todo {
		id: number;
		content: string;
		completed: boolean;
	}

	let editingTodo: Todo = $state({ id: 0, content: '', completed: false });

	function makeTodo(id: number, content: string, completed: boolean): Todo {
		return { id, content, completed };
	}

	let todos: Todo[] = $state([
		makeTodo(1, '할일 1', false),
		makeTodo(2, '할일 2', false),
		makeTodo(3, '할일 3', false)
	]);

	const completed = $derived(todos.filter((todo) => todo.completed).length);
	$effect(() => {
		for (const todo of todos) console.log(todo.content, todo.completed);
	});

	function addTodo(this: HTMLFormElement, event: Event) {
		const form = this;

		if (form.todoContent.value.trim().length === 0) return;

		todos.push(makeTodo(todos.length + 1, form.todoContent.value, false));
		form.todoContent.value = '';
		form.todoContent.focus();
	}

	function showTodoEditModal(todo: Todo) {
		Object.assign(editingTodo, todo);

		const modal = document.getElementById('todo-edit-modal') as HTMLDialogElement;
		modal.showModal();

		modal.querySelector('input')?.focus();
	}

	function submitEditTodoForm(this: HTMLFormElement, event: Event) {
		const form = this;

		todos = todos.map((todo) =>
			todo.id == editingTodo.id ? { ...todo, content: editingTodo.content } : todo
		);

		const modal = document.getElementById('todo-edit-modal') as HTMLDialogElement;
		modal.close();
	}
</script>

<h1>할일</h1>
<h2>할일 추가({completed}/{todos.length})</h2>
<form on:submit|preventDefault={addTodo}>
	<input name="todoContent" type="text" class="input input-bordered" placeholder="할일" />
	<input type="submit" value="추가" class="btn btn-primary" />
</form>

{#snippet TodoList(todos)}
	<h2>할일 목록</h2>
	<ul class="grid gap-2">
		{#each todos as todo (todo.id)}
			<li class="flex items-center gap-2">
				{todo.content}
				<input type="checkbox" class="checkbox" bind:checked={todo.completed} />
				<button class="btn btn-outline btn-sm" onclick={() => showTodoEditModal(todo)}>수정</button>
				<button class="btn btn-outline btn-sm">삭제</button>
			</li>
		{/each}
	</ul>
{/snippet}

<dialog id="todo-edit-modal" class="modal">
	<div class="modal-box">
		<form method="dialog">
			<button class="btn btn-circle btn-ghost btn-sm absolute right-2 top-2">✕</button>
		</form>
		<form class="grid gap-3" on:submit|preventDefault={submitEditTodoForm}>
			<label class="form-control">
				<div class="label">
					<span class="label-text">할일</span>
				</div>
				<input
					type="text"
					placeholder="할일을 입력해주세요."
					class="input input-bordered"
					bind:value={editingTodo.content}
				/>
			</label>
			<div class="grid grid-cols-2 gap-3">
				<button class="btn btn-primary">저장</button>
				<button
					type="button"
					class="btn btn-outline"
					onclick={() => {
					const modal = document.getElementById('todo-edit-modal') as HTMLDialogElement;
					modal.close();
				}}
					>취소</button
				>
			</div>
		</form>
	</div>

	<form method="dialog" class="modal-backdrop">
		<button></button>
	</form>
</dialog>

{@render TodoList(todos)}
<hr />
{@render TodoList(todos)}
