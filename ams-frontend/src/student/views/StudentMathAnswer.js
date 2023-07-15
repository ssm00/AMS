import React from "react";

// components


import StudentEngAnswerForm from "../component/AnswerInput/StudentEngAnswerForm";
import StudentSidebar from "../component/Sidebar/StudentSidebar.js";
import StudentHeaderStats from "../component/Header/StudentHeaderStats.js";
import StudentAnswerHeader from "../component/Header/StudentAnswerHeader";
import {call} from "../../service/ApiService";

export default function StudentMathAnswer() {
    const [examNumber, setExamNumber] = React.useState(1);
    call("/teachers/average-graph", "POST", {"grade" : 3, "examSubject" : "ENGLISH"}).then((response) => {
        setExamNumber(response.result.eachAverageScoreList.length+1);
    }).catch((error) => {
        return;
    });
    return (
        <>
            <StudentSidebar />
            <div className="relative md:ml-64 bg-blueGray-100">
                <StudentAnswerHeader
                    examNumber={examNumber}
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
