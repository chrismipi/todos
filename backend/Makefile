clean:
	./gradlew clean

compile:
	./gradlew build

tests:
	./gradlew test -i

build-image: clean compile
	docker build -t chrismipi/todo-backend .

push-image:
	docker push chrismipi/todo-backend