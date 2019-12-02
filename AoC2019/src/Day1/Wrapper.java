package Day1;

import java.util.function.Consumer;

public class Wrapper<I> {
    public Wrapper(Consumer<I> func) {
		this.func = func;
	}

	public Consumer<I> func;
}
