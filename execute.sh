java -cp vnu.jar nu.validator.servlet.Main 8888 &
sleep 5;
java -jar target/Descartes2HTML5-1.0.0.jar src/main/config/html5converter.properties &
