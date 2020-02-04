#version 330

struct material_t {
	vec3 ambient_color;
	vec3 diffuse_color;
	vec3 specular_color;
	vec3 specular_exp;
};

out vec4 o_frag_color;

uniform material_t u_material;

void main()
{
	o_frag_color = vec4(u_material.diffuse_color, 1.0);
}