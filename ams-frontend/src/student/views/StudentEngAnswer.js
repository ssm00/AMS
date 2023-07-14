import React from "react";

// components


import StudentEngAnswerForm from "../component/AnswerInput/StudentEngAnswerForm";
import StudentSidebar from "../component/Sidebar/StudentSidebar.js";
import StudentHeaderStats from "../component/Header/StudentHeaderStats.js";

export default function StudentEngAnswer() {
    const [examNumber, setExamNumber] = React.useState(1);
    function changeExamNumber(examNumber){
        setExamNumber(examNumber);
    }
    return (
        <>
            <StudentSidebar />
            <div className="relative md:ml-64 bg-blueGray-100">
                <StudentHeaderStats
                    changeExamNumber={changeExamNumber}
                />
                <div className="px-4 md:px-10 mx-auto w-full -m-24">
                    <div className="flex flex-wrap">
                        <div className="w-full mb-12 xl:mb-0 px-4">
                            <StudentEngAnswerForm
                                examNumber={examNumber}
                            />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
