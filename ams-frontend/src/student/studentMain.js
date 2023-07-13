import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

// components
import Sidebar from "./component/Sidebar/StudentSidebar.js";


// views
import EnglishMain from "../student/views/english";
import EnglishAnswer from "../student/views/StudentEngAnswer";

export default function StudentMain() {
  return (
    <>
      <Sidebar />
      <div className="relative md:ml-64 bg-blueGray-100">
        {/* <AdminNavbar /> */}
        {/* Header */}
        {/* <HeaderStats /> */}
        <div className="px-4 md:px-10 mx-auto w-full -m-24">
          <Routes>
            <Route path="/student/english/main" element={EnglishMain} />
            <Route path="/student/english/inputAnswer" element={EnglishAnswer} />
            {/* <Route path="/student/english/main" exact component={EnglishMain} /> */}
            {/* <Redirect from="/student" to="/student/english/main" /> */}
            <Route path="/student" element={<Navigate replace to="/student/english/main"/>} />     
          </Routes>
          {/* <FooterAdmin /> */}
        </div>
      </div>
    </>
  );
}
