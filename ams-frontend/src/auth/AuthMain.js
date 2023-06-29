import React from "react";
import {Routes, Route, Navigate} from "react-router-dom";

// components

import Navbar from "../components/Navbars/AuthNavbar.js";
import FooterSmall from "../components/Footers/FooterSmall.js";

// views

import Login from "./Login.js";
import SignUp from "./SignUp.js";

export default function Auth() {
  return (
    <>
      <Navbar transparent />
      <main>
        <section className="relative w-full h-full py-40 min-h-screen">
          <div
            className="absolute top-0 w-full h-full bg-blueGray-800 bg-no-repeat bg-full"
            style={{
              backgroundImage:
                "url(" + require("../assets/img/register_bg_2.png").default + ")",
            }}
          ></div>
                <div>
                    <Routes>
                        <Route path="/users" element={<Navigate to="/users/log-in"></Navigate>} />
                        <Route path="/users/log-in" element={<Login />} />
                        <Route path="/users/sign-up" element={<SignUp />} />
                    </Routes>
                </div>
          <FooterSmall absolute />
        </section>
      </main>
    </>
  );
}
