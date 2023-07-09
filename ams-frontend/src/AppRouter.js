import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import React from "react";
import TeacherMain from "./teacher/TeacherMain";
import Login from "./auth/Login";
import SignUp from "./auth/SignUp";
import TeacherAnswer from "./teacher/TeacherAnswer";
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
                            <Route path="/teacher/answer" element={<TeacherAnswer />} />
                        </Routes>
                    </div>
                </Router>
            </div>
            </>
        );
    }
}

export default AppRouter;
