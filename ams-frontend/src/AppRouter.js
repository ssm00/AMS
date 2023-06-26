import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import React from "react";
import TeacherMain from "./teacher/TeacherMain";
import Test from "./teacher/Test";
class AppRouter extends React.Component {
    render() {
        return (
            <div>
                <Router>
                    <div>
                        {/* React-router-dom V6 이후 Switch -> routes로 바뀜  Route 안에 element 사용 */}
                        <Routes>
                            {/*<Route path="/" element={<TeacherMain />} />*/}
                            <Route path="/" element={<TeacherMain />} />
                        </Routes>
                    </div>
                </Router>
            </div>
        );
    }
}

export default AppRouter;
