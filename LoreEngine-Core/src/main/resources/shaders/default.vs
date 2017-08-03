#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec4 color;
layout (location = 2) in vec2 texCoord;

uniform mat4 projection;
uniform mat4 view;
uniform mat4 transform;

out vec3 vPosition;
out vec4 vColor;
out vec2 vTexCoord;

void main()
{
	gl_Position = projection * view * transform * vec4(position, 1.0);
	vPosition = position;
	vColor = color;
	vTexCoord = texCoord;
}