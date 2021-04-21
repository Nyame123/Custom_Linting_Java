package com.ecomtrading.mycustomlintbutton.gradingSystem;

public class GradingSystemMain {

    public static void main(String[] args) {

        GradingSystemMain gradingSystem = new GradingSystemMain();
        //creating a student record
        Student info = new Student(14,25,100);
        gradingSystem.willGetCertificate(info);
    }

    //Check if the Exams was passed
    private boolean isExamsPassed(double exams) {
        if (exams >= 25) {
            return true;
        } else {
            return false;
        }

    }

    //Check if the Assessment was passed
    private boolean isAssessmentPassed(double assessment) {
        if (assessment >= 15) {
            return true;
        } else {
            return false;
        }
    }

    //Check if the fees fully paid
    private boolean isFullyPaid(double fees) {
        if (fees >= 100) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the student failed
     *
     * @param studentInfo
     * @return true if passed
     */
    private boolean didStudentPassed(Student studentInfo) {
        if (isExamsPassed(studentInfo.getExamMark())
                && isAssessmentPassed(studentInfo.getAssessmentMark())) {
            System.out.format("\nYou passed Exams with %s and Assessment with %s", studentInfo.getExamMark(),
                    studentInfo.getAssessmentMark());
            return true;
        } else if (
                (studentInfo.getExamMark() >= 25 && studentInfo.getAssessmentMark() >= 14)
                        || (studentInfo.getExamMark() >= 24 && studentInfo.getAssessmentMark() >= 15)
        ) {
            System.out.format("\nYou passed Exams with %s and Assessment with %s totaling 39",
                    studentInfo.getExamMark(), studentInfo.getAssessmentMark());
            return true;
        } else {
            if (!isAssessmentPassed(studentInfo.getAssessmentMark())){
                System.out.format("\nYou failed Assessment with %s.\nYou are repeated!!!",studentInfo.getAssessmentMark());
            }

            if (!isExamsPassed(studentInfo.getExamMark())){
                System.out.format("\nYou failed Exams with %s.\nYou are repeated!!!",studentInfo.getExamMark());
            }
            return false;
        }
    }

    /**
     * Check if the student will get a certificate
     * @param info
     *
     */
    private void willGetCertificate(Student info) {
        if (isFullyPaid(info.getFeePaid()) && didStudentPassed(info)) {
            System.out.format("\nYou will have your certificate.");
        } else {
            System.out.format("\nYou will not have your certificate because you have failed %s.",
                    !isFullyPaid(info.getFeePaid())? " to pay your fees in full"
                            : !didStudentPassed(info)? " to pass your exams" :
                            "Did not pay your fees in full and did not pass your exams and assessment");
        }
    }


    private static class Student {
        private double assessmentMark;
        private double examMark;
        private double feePaid;

        public Student(double assessmentMark, double examMark, double feePaid) {
            this.assessmentMark = assessmentMark;
            this.examMark = examMark;
            this.feePaid = feePaid;
        }

        public double getAssessmentMark() {
            return assessmentMark;
        }

        public void setAssessmentMark(double assessmentMark) {
            this.assessmentMark = assessmentMark;
        }

        public double getExamMark() {
            return examMark;
        }

        public void setExamMark(double examMark) {
            this.examMark = examMark;
        }

        public double getFeePaid() {
            return feePaid;
        }

        public void setFeePaid(double feePaid) {
            this.feePaid = feePaid;
        }
    }
}
