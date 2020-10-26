#include <string>

class Particle {
private:
	
	int32_t color;
	
	int32_t sprite;
	
	std::string name;
	
public:
	Particle() = delete;
	
	Particle(color, sprite, name) {
		this->color = color;
		this->sprite = sprite;
		this->name = name;
	}
	
	~Particle() = default;
};

class Bullet {
private:
	
	int64_t x;
	
	int64_t y;
	
	int64_t z;
	
	Particle* type;
public:
	Bullet() = delete;
	
	Bullet(int64_t x, int64_t y, int64_t z, Particle* type) {
		this->x = x;
		this->y = y;
		this->z = z;
		this->type = type;
	}
	
	~Bullet() = default;
};


signed main() {
	return 0;
}
