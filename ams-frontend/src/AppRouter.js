import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import React from "react";
import TeacherMain from "./teacher/TeacherMain";
import Login from "./auth/Login";
import SignUp from "./auth/SignUp";
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
                            <Route path="/teacher" element={<TeacherMain />} />
                            <Route path="/users/*" element={<Login />} />
                            <Route path="/login" element={<Login />} />
                            <Route path="/sign-up" element={<SignUp />} />
                            <Route path="/student/english/main" element={<EnglishMain />} />
                            <Route path="/student" element={<Navigate replace to="/student/english/main"/>} />   
                        </Routes>
                    </div>
                </Router>
            </div>
            </>
        );
    }
}

export default AppRouter;
