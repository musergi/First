#version 330

layout (location = 0) in vec3 v_position;

uniform mat4 u_projection_matrix;
uniform mat4 u_view_matrix;
uniform mat4 u_transformation_matrix;

void main()
{
	gl_Position = u_projection_matrix * u_view_matrix * u_transformation_matrix * vec4(v_position, 1.0);
}