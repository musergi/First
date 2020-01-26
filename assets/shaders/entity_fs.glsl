#version 330

struct material_t {
	vec3 ambient_color;
	vec3 diffuse_color;
	vec3 specular_color;
	float specular_exponent;
};

struct point_light_t {
	vec3 color;
	vec3 position;
	float intensity;
};

in vec3 p_world_position;
in vec3 p_world_normal;

out vec4 o_frag_color;

uniform material_t u_material;
uniform vec3 u_ambient_light_color;
uniform point_light_t u_point_light;

void main()
{
	vec3 to_light_direction = normalize(u_point_light.position - p_world_position);
	float diffuse_factor = max(dot(to_light_direction, p_world_normal), 0.0);
	vec3 diffuse_light = u_material.diffuse_color * u_point_light.color * diffuse_factor * u_point_light.intensity;

	o_frag_color = vec4(u_ambient_light_color, 1.0) * vec4(u_material.ambient_color, 1.0) + vec4(diffuse_light, 1.0);
}
