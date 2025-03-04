import static org.lwjgl.opengl.GL30.*;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

public class Renderer {
    private int vaoID, vboID;
    private ShaderProgram shaderProgram;

    public void init() {
        shaderProgram = new ShaderProgram("src/res/shaders/vertexShader.vert",
                "src/res/shaders/fragmentShader.frag");

        float[] vertices = {
                0.0f, 0.0f, 0.0f,
                0.4f,0.5f,0.0f,
                0.0f,0.5f,0.0f
        };


        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertexBuffer.put(vertices).flip();
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void render() {

        shaderProgram.use();
        glPointSize(10.0f);
        glBindVertexArray(vaoID);
        glDrawArrays(GL_TRIANGLES, 0, 3);
        glBindVertexArray(0);
        glPolygonMode(GL_FRONT_AND_BACK,GL_FILL);
    }
}
