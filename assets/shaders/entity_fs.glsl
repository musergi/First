#version 330

struct material_t {
	vec3 ambient_color;
	vec3 diffuse_color;
	vec3 specular_color;
	float specular_exponent;
};

struct attenuation_t {
	float constant;
	float linear;
	float exponent;
};

struct point_light_t {
	vec3 color;
	vec3 position;
	float intensity;
	attenuation_t att;
};

in vec3 p_world_position;
in vec3 p_view_world_position;
in vec3 p_world_normal;

out vec4 o_frag_color;

uniform material_t u_material;
uniform vec3 u_ambient_light_color;
uniform point_light_t u_point_light;

void main()
{
	vec3 to_light = u_point_light.position - p_world_position;

	vec3 to_light_direction = normalize(to_light);
	float diffuse_factor = max(dot(to_light_direction, p_world_normal), 0.0);
	vec3 diffuse_light = u_material.diffuse_color * u_point_light.color * diffuse_factor * u_point_light.intensity;
	
	vec3 to_camera_direction = normalize(-p_view_world_position);
	vec3 from_light_direction = -to_light_direction;
	vec3 reflected_light = normalize(reflect(from_light_direction, p_world_normal));
	float specular_factor = max(dot(reflected_light, to_camera_direction), 0.0);
	vec3 specular_light = u_material.specular_color * u_point_light.color * pow(specular_factor, u_material.specular_exponent);
	
	float dist = length(to_light);
	float attenuation = 1.0 / (u_point_light.att.constant + u_point_light.att.linear * dist + u_point_light.att.exponent * dist * dist);

	vec3 diffuse_specular_component = (diffuse_light + specular_light) * attenuation;
	 
	o_frag_color = vec4(u_ambient_light_color, 1.0) * vec4(u_material.ambient_color, 1.0) + vec4(diffuse_specular_component, 1.0);
}
