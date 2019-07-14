clean:
	./gradlew clean

compile: clean
	./gradlew build

tests: clean
	./gradlew test -i

build-image: clean compile
	docker build -t chrismipi/todo-backend .

push-image:
	docker push chrismipi/todo-backend