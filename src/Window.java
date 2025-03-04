import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL46;

public class Window {
    private long window;
    private int width;
    private int height;
    private String title;
    private Renderer render;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init() {
        if (!GLFW.glfwInit()) {

            throw new IllegalStateException("Problem with initialization of GLFW.");
        }

        window = GLFW.glfwCreateWindow(width,
                height,
                title,
                0,
                0);

        if (window == 0) {

            throw new RuntimeException("Could not create the window!");
        }

        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL46.glViewport(0, 0, width, height);
        GL46.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);

        render = new Renderer();
        render.init();
    }

    private void loop() {

        while (!GLFW.glfwWindowShouldClose(window)) {
            GL46.glClear(GL46.GL_COLOR_BUFFER_BIT);
            render.render();
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    private void cleanUp(){
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public void run(){
        init();
        loop();
        cleanUp();
    }
}