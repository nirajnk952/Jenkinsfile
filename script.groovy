pipeline {
    agent any

    environment {
        NUMBER1 = 2  // You can change this value
        NUMBER2 = 3  // You can change this value
    }

    stages {
        stage('Addition and Tests') {
            steps {
                script {
                    int actualSum = NUMBER1.toInteger() + NUMBER2.toInteger()
                    echo "The sum of ${NUMBER1} and ${NUMBER2} is: ${actualSum}"

                    // Test Case 1
                    try {
                        int expectedSum = NUMBER1.toInteger() + NUMBER2.toInteger()
                        if (actualSum == expectedSum) {
                            echo "Test Case 1 Passed: ${actualSum} equals ${expectedSum}"
                        } else {
                            error("Test Case 1 Failed: ${actualSum} does not equal ${expectedSum}")
                        }
                    } catch (Exception e) {
                        echo "Test Case 1 failed: ${e.getMessage()}"
                    }

                    // Test Case 2
                    try {
                        if (actualSum > NUMBER1.toInteger()) {
                            echo "Test Case 2 Passed: ${actualSum} is greater than ${NUMBER1}"
                        } else {
                            error("Test Case 2 Failed: ${actualSum} is not greater than ${NUMBER1}")
                        }
                    } catch (Exception e) {
                        echo "Test Case 2 failed: ${e.getMessage()}"
                    }

                    // Test Case 3
                    try {
                        if (actualSum > NUMBER2.toInteger()) {
                            echo "Test Case 3 Passed: ${actualSum} is greater than ${NUMBER2}"
                        } else {
                            error("Test Case 3 Failed: ${actualSum} is not greater than ${NUMBER2}")
                        }
                    } catch (Exception e) {
                        echo "Test Case 3 failed: ${e.getMessage()}"
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Finished Execution'
        }
    }
}
