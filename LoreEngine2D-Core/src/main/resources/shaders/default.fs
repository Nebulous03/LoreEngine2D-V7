#version 330 core

out vec4 fragColor;

in vec3 vPosition;
in vec2 vTexCoord;

uniform sampler2D vTexture;

void main()
{
	fragColor = texture(vTexture, vTexCoord); 
}
