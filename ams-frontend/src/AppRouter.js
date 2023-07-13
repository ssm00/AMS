import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import React from "react";
import TeacherEngMain from "./teacher/TeacherEngMain";
import Login from "./auth/Login";
import SignUp from "./auth/SignUp";
import TeacherEngAnswerMain from "./teacher/views/TeacherEngAnswerMain";
import StudentEngAnswer from "./teacher/StudentEngAnswer";

import EnglishMain from "./student/views/english";

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
                            <Route path="/teacher/english/main" element={<TeacherEngMain />} />
                            <Route path="/teacher/english/answer" element={<TeacherEngAnswerMain />} />

                            {/*student*/}
                            <Route path="/student" element={<Navigate replace to="/student/english/main"/>} />
                            <Route path="/student/english/answer" element={<StudentEngAnswer />} />
                            <Route path="/student/english/main" element={<EnglishMain />} />

                        </Routes>
                    </div>
                </Router>
            </div>
            </>
        );
    }
}

export default AppRouter;
