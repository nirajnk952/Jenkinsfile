pipeline {
    agent any

    environment {
        NUMBER1 = 5  
        NUMBER2 = 7  
    }

    stages {
        stage('Initialize') {
            steps {
                script {
                    echo "Initializing pipeline with NUMBER1: ${NUMBER1} and NUMBER2: ${NUMBER2}"
                }
            }
        }

        stage('Calculate Sum') {
            steps {
                script {
                    int actualSum = NUMBER1.toInteger() + NUMBER2.toInteger()
                    echo "The sum of ${NUMBER1} and ${NUMBER2} is: ${actualSum}"
                    env.ACTUAL_SUM = actualSum.toString()
                }
            }
            post {
                always {
                    echo "Finished Calculate Sum stage"
                }
            }
        }

        stage('Test Case 1: Validate Sum') {
            steps {
                script {
                    try {
                        int expectedSum = NUMBER1.toInteger() + NUMBER2.toInteger()
                        int actualSum = env.ACTUAL_SUM.toInteger()
                        if (actualSum == expectedSum) {
                            echo "Test Case 1 Passed: ${actualSum} equals ${expectedSum}"
                        } else {
                            error("Test Case 1 Failed: ${actualSum} does not equal ${expectedSum}")
                        }
                    } catch (Exception e) {
                        echo "Test Case 1 failed: ${e.getMessage()}"
                    }
                }
            }
            post {
                always {
                    echo "Finished Test Case 1 stage"
                }
            }
        }

        stage('Test Case 2: Check if Sum is Greater than NUMBER1') {
            steps {
                script {
                    try {
                        int actualSum = env.ACTUAL_SUM.toInteger()
                        if (actualSum > NUMBER1.toInteger()) {
                            echo "Test Case 2 Passed: ${actualSum} is greater than ${NUMBER1}"
                        } else {
                            error("Test Case 2 Failed: ${actualSum} is not greater than ${NUMBER1}")
                        }
                    } catch (Exception e) {
                        echo "Test Case 2 failed: ${e.getMessage()}"
                    }
                }
            }
            post {
                always {
                    echo "Finished Test Case 2 stage"
                }
            }
        }

        stage('Test Case 3: Check if Sum is Greater than NUMBER2') {
            steps {
                script {
                    try {
                        int actualSum = env.ACTUAL_SUM.toInteger()
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
            post {
                always {
                    echo "Finished Test Case 3 stage"
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline Execution Finished'
        }
    }
}
