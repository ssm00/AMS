import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import React from "react";
import TeacherEngMain from "./teacher/TeacherEngMain";
import Login from "./auth/Login";
import SignUp from "./auth/SignUp";
import TeacherEngAnswerMain from "./teacher/views/TeacherEngAnswerMain";
import StudentEngAnswer from "./student/views/StudentEngAnswer";

import StudentEngMain from "./student/views/StudentEngMain";
import TeacherEngModifyMain from "./teacher/views/TeacherEngModifyMain";
import StudentMathAnswer from "./student/views/StudentMathAnswer";
import StudentMathMain from "./student/views/StudentMathMain";
import TeacherMathMain from "./teacher/TeacherMathMain";
import TeacherMathAnswerMain from "./teacher/views/TeacherMathAnswerMain";
import TeacherMathModifyMain from "./teacher/views/TeacherMathModifyMain";

class AppRouter extends React.Component {
    render() {
        return (
            <>
            <div>
                <Router>
                    <div>
                        {/* React-router-dom V6 이후 Switch -> routes로 바뀜  Route 안에 element 사용 */}
                        <Routes>
                            {/*all*/}
                            <Route path="/users/*" element={<Login />} />
                            <Route path="/login" element={<Login />} />
                            <Route path="/sign-up" element={<SignUp />} />
                            <Route path="/" element={<Navigate replace to="/login"/>} />

                            {/*teacher*/}
                            <Route path="/teacher" element={<Navigate replace to="/teacher/english/main"/>} />
                            <Route path="/teacher/english/main" element={<TeacherEngMain />} />
                            <Route path="/teacher/english/answer" element={<TeacherEngAnswerMain />} />
                            <Route path="/teacher/english/modify" element={<TeacherEngModifyMain />} />
                            <Route path="/teacher/math/main" element={<TeacherMathMain />} />
                            <Route path="/teacher/math/answer" element={<TeacherMathAnswerMain />} />
                            <Route path="/teacher/math/modify" element={<TeacherMathModifyMain />} />

                            {/*student*/}
                            <Route path="/student" element={<Navigate replace to="/student/english/main"/>} />
                            <Route path="/student/english/answer" element={<StudentEngAnswer />} />
                            <Route path="/student/english/main" element={<StudentEngMain />} />
                            <Route path="/student/math/answer" element={<StudentMathAnswer />} />
                            <Route path="/student/math/main" element={<StudentMathMain />} />

                        </Routes>
                    </div>
                </Router>
            </div>
            </>
        );
    }
}

export default AppRouter;
