#version 330 core

out vec4 fragColor;

in vec3 vPosition;

void main()
{
	fragColor = vec4(1.0, 1.0, 1.0, 1.0); //vec4(clamp(vPosition, 0.0, 1.0), 1.0);
}
