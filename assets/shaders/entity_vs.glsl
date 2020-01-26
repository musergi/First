#version 330

layout (location = 0) in vec3 v_position;
layout (location = 1) in vec3 v_normal;

uniform mat4 u_projection_matrix;
uniform mat4 u_view_matrix;
uniform mat4 u_model_matrix;

out vec3 p_world_position;
out vec3 p_world_normal;

void main()
{
	vec4 world_position = u_model_matrix * vec4(v_position, 1.0);

	gl_Position = u_projection_matrix * u_view_matrix * world_position;
	p_world_position = world_position.xyz;
	p_world_normal = normalize((u_model_matrix * vec4(v_normal, 0.0)).xyz);
}
