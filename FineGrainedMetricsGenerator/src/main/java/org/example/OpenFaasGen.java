package org.example;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

public class OpenFaasGen {

    //private static final double DISTRIBUTION_PATTERN_1 = 0.3;
    //private static final double DISTRIBUTION_PATTERN_2 = 0.3;
    //private static final double DISTRIBUTION_PATTERN_3 = 0.3;
    //private static final int TOTAL_REPETITIONS = 16;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        generateHandlerClass(1, 1, 1);
        generateHandlerClass(6, 6, 6);
        generateHandlerClass(12, 12, 12);
        generateHandlerClass(25, 25, 25);
        generateHandlerClass(50, 50, 50);
    }

    private static void generateHandlerClass(int repetitions1, int repetitions2, int repetitions3) {
        // Specify the package and class names
        String packageName = "com.openfaas.function";
        final int total = repetitions1 + repetitions2 + repetitions3;
        String className = "Handler" + total;

        // Get existing class names in the package
        Set<String> existingClassNames = getExistingClassNames(packageName);

        // Generate a unique class name
        String uniqueClassName = generateUniqueClassName(className, existingClassNames);


        // Create a TypeName for the AbstractHandler class
        TypeName abstractHandlerType = ClassName.get("com.openfaas.model", "AbstractHandler");
        TypeName stringType = ClassName.get("java.lang", "String");

        // Create a TypeSpec for the Handler class
        TypeSpec.Builder handlerClassBuilder  = TypeSpec.classBuilder(uniqueClassName)
                .addModifiers(Modifier.PUBLIC)
                .superclass(abstractHandlerType)
                .addMethod(generateHandleRequestMethod(stringType, repetitions1, repetitions2, repetitions3));

        // Add the func1() method
        handlerClassBuilder.addMethod(generateFuncMethod(stringType, 1));
        handlerClassBuilder.addMethod(generateFuncMethod(stringType, 2));
        handlerClassBuilder.addMethod(generateFuncMethod(stringType, 3));
        handlerClassBuilder.addMethod(generateFuncMethod(stringType, 4));

        // Build the TypeSpec for the Handler class
        TypeSpec handlerClass = handlerClassBuilder.build();

        // Create a JavaFile for the package and class
        JavaFile javaFile = JavaFile.builder(packageName, handlerClass)
                .build();

        // Specify the directory to save the generated file
        Path outputDirectory = Paths.get("src/main/java");

        // Write the JavaFile to the file system
        try {
            javaFile.writeTo(outputDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static MethodSpec generateHandleRequestMethod(TypeName stringType, int repetitions1, int repetitions2, int repetitions3) {
        // Create a TypeName for the IRequest and IResponse interfaces
        TypeName requestType = ClassName.get("com.openfaas.model", "IRequest");
        TypeName iresponseType = ClassName.get("com.openfaas.model", "IResponse");
        TypeName responseType = ClassName.get("com.openfaas.model", "Response");
        TypeName metricsLibType = ClassName.get("metrics", "MetricsLib");
        TypeName metricType = ClassName.get("metrics", "Metric");

        // Create the method signature
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("Handle")
                .addModifiers(Modifier.PUBLIC)
                .returns(iresponseType)
                .addParameter(requestType, "req");

        // Add the method body
        methodBuilder.addStatement("final $T metrics = new $T()", metricsLibType, metricsLibType);

        // Calculate the number of repetitions based on the distribution percentage
        //int repetitions1 = (int) Math.ceil(distributionPattern1 * totalRepetitions);
        // Add the first code pattern based on the distribution
        for (int i = 0; i < repetitions1; i++) {
            methodBuilder.addCode("\n")
                    .addStatement("final $T id" + i + " = metrics.startTime()", stringType)
                    .addStatement("System.out.println(func1())")
                    .addStatement("System.out.println(func2())")
                    .addStatement("metrics.endTime(id" + i + ")")
                    .addCode("\n");
        }

        // Calculate the number of repetitions based on the distribution percentage
        //int repetitions2 = (int) Math.ceil(distributionPattern2 * totalRepetitions);
        // Add the second code pattern based on the distribution
        for (int i = 0; i < repetitions2; i++) {
            methodBuilder.addCode("\n")
                    .beginControlFlow("if (true)")
                    .addStatement("System.out.println(func3())")
                    .addStatement("metrics.incCounter()")
                    .endControlFlow()
                    .addCode("\n");
        }

        // Calculate the number of repetitions based on the distribution percentage
        // int repetitions3 = (int) Math.ceil(distributionPattern3 * totalRepetitions);
        // Add the third code pattern based on the distribution
        for (int i = 0; i < repetitions3; i++) {
            methodBuilder.addCode("\n")
                    .addStatement("$T metric" + i + " = null", metricType)
                    .beginControlFlow("for (int i = 0; i < 4; i++)")
                    .addStatement("System.out.println(func4())")
                    .addStatement("metric" + i + " = metrics.incCounter(metric" + i + " != null ? metric" + i + ".getName() : null)")
                    .endControlFlow()
                    .addCode("\n");
        }

        methodBuilder.addStatement("$T end = metrics.end()", stringType)
                .addStatement("$T res = new $T()", iresponseType, responseType)
                .addStatement("res.setBody(end)")
                .addStatement("return res");

        return methodBuilder.build();
    }

    private static MethodSpec generateFuncMethod(TypeName stringType, int number) {
        // Create the method signature
        return MethodSpec.methodBuilder("func" + number)
                .addModifiers(Modifier.PUBLIC)
                .returns(stringType)
                .addStatement("return \"function"+ number + "\"")
                .build();
    }


    private static Set<String> getExistingClassNames(String packageName) {
        // Get the existing class names in the specified package
        try {
            return Files.walk(Paths.get("src/main/java/" + packageName.replace('.', '/')))
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .map(fileName -> fileName.substring(0, fileName.lastIndexOf('.')))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Set.of();
    }

    private static String generateUniqueClassName(String baseClassName, Set<String> existingClassNames) {
        String uniqueClassName = baseClassName;
        int counter = 2;
        while (existingClassNames.contains(uniqueClassName)) {
            uniqueClassName = baseClassName + counter;
            counter++;
        }
        return uniqueClassName;
    }
}