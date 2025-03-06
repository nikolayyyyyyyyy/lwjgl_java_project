#version 460 core
layout(location = 0) in vec3 aPos; // Позиция на върха
layout(location = 1) in vec3 aColor; // Цвят на върха
out vec3 vertexColor; // Изход за фрагментния шейдър
void main() {
    gl_Position = vec4(aPos, 1.0);
    vertexColor = aColor; // Предаване на цвета към фрагментния шейдър
}