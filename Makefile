package:
	@ gradle clean bootJar
docker-image-build: package
	@ docker build -t giuliaarruda/challenge-school .
run: docker-image-build
	@ docker compose up -d
stop:
	@ docker compose down -v