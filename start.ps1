mvn package
if($?){
java -jar .\target\springboot-0.0.1.jar
} else{
    Write-Host "build failed not running" -ForegroundColor Red
}