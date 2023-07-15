import React, {useState} from "react";
import {call} from "../../../service/ApiService.js";

// components

export default function CardStudentGrade({examNumber, examSubject}) {
  const [data, setData] = useState([]);
  const [problemList, setProblemList] = useState([]);
  const [isFetched, setIsFetched] = useState(false);

  React.useEffect(() => {
    call("/users/grade-card", "POST", {"examNumber": examNumber ,"grade" : 3, "examSubject" : examSubject}).then((response) => {
      setData(response.result);
      setProblemList(response.result.eachProblemInfoList);
      setIsFetched(true);
    }).catch((error) => {
      return;
    });
  },[examNumber,isFetched]);
  return (
    <>
      <div className="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-lg rounded">
        <div className="rounded-t mb-0 px-4 py-3 border-0">
          <div className="flex flex-wrap items-center">
            <div className="relative w-full px-4 max-w-full flex-grow flex-1">
              <h3 className="font-semibold text-base text-blueGray-700">
                {examNumber}회차 개인 성적표
              </h3>
            </div>
          </div>
        </div>
        <div className="block w-full overflow-x-auto">
          {/* Projects table */}
          <table className="items-center w-full bg-transparent border-collapse">
            <thead>
              <tr>
                <th className="px-6 bg-blueGray-50 text-blueGray-500 align-middle border border-solid border-blueGray-100 py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-left">
                  등수: {isFetched ? data.studentRank : "-"} / {isFetched ? data.totalStudents : "-"}
                </th>
                <th className="px-6 bg-blueGray-50 text-blueGray-500 align-middle border border-solid border-blueGray-100 py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-left">
                  점수: {isFetched ? data.studentScore : "-"} / {isFetched ? data.perfectScore : "-"}
                </th>
                <th className="px-6 bg-blueGray-50 text-blueGray-500 align-middle border border-solid border-blueGray-100 py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-left">
                </th>
                <th className="px-6 bg-blueGray-50 text-blueGray-500 align-middle border border-solid border-blueGray-100 py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-left">
                </th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4 text-left">
                  오답표
                </th>
              </tr>
            </tbody>
            <th>
            <table>
              <thead>
                <tr>
                  <th className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4 text-center">
                    번호
                  </th>
                  <th className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4 text-center">
                    정답
                  </th>
                  <th className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4 text-center">
                    내 답
                  </th>
                  <th className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4 text-center">
                    오답률
                  </th>
                </tr>
              </thead>
              <tbody>
                {problemList.map((problemInfo) => {
                  if(isFetched && problemInfo.problemNumber > 15){
                    return null;
                  }
                  return (
                    <tr>
                      <td className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4">
                        {isFetched ? problemInfo.problemNumber : ""}
                      </td>
                      <td className="px-6 align-middle border border-solid text-xs whitespace-nowrap p-4">
                        {isFetched ? problemInfo.examAnswer : ""}
                      </td>
                      <td className={isFetched && problemInfo.examAnswer===problemInfo.studentAnswer ? 
                        "text-lightBlue-500 px-6 align-middle border border-solid text-xs whitespace-nowrap p-4"
                         : "text-red-500 px-6 align-middle border border-solid text-xs whitespace-nowrap p-4"}>
                        {isFetched ? problemInfo.studentAnswer : ""}
                      </td>
                      <td className="px-6 align-middle border border-solid text-xs whitespace-nowrap p-4">
                        <i className="fas fa-arrow-down text-orange-500 mr-4"></i>
                        {isFetched ? (problemInfo.wrongRate * 100) + "%" : ""}
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
            </th>
            <th>
            <table>
              <thead>
                <tr>
                  <th className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4 text-center">
                    번호
                  </th>
                  <th className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4 text-center">
                    정답
                  </th>
                  <th className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4 text-center">
                    내 답
                  </th>
                  <th className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4 text-center">
                    오답률
                  </th>
                </tr>
              </thead>
              <tbody>
              {problemList.map((problemInfo) => {
                  if(isFetched && problemInfo.problemNumber > 15 && problemInfo.problemNumber < 31){
                    return (
                      <tr>
                        <td className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4">
                          {isFetched ? problemInfo.problemNumber : ""}
                        </td>
                        <td className="px-6 align-middle border border-solid text-xs whitespace-nowrap p-4">
                          {isFetched ? problemInfo.examAnswer : ""}
                        </td>
                        <td className={isFetched && problemInfo.examAnswer===problemInfo.studentAnswer ? 
                          "text-lightBlue-500 px-6 align-middle border border-solid text-xs whitespace-nowrap p-4"
                           : "text-red-500 px-6 align-middle border border-solid text-xs whitespace-nowrap p-4"}>
                          {isFetched ? problemInfo.studentAnswer : ""}
                        </td>
                        <td className="px-6 align-middle border border-solid text-xs whitespace-nowrap p-4">
                          <i className="fas fa-arrow-down text-orange-500 mr-4"></i>
                          {isFetched ? (problemInfo.wrongRate * 100) + "%" : ""}
                        </td>
                      </tr>
                    );
                  }
                  else return null;
                })}
              </tbody>
          
            </table>
            </th>
            <th>
            <table>
              <thead>
                <tr>
                  <th className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4 text-center">
                    번호
                  </th>
                  <th className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4 text-center">
                    정답
                  </th>
                  <th className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4 text-center">
                    내 답
                  </th>
                  <th className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4 text-center">
                    오답률
                  </th>
                </tr>
              </thead>
              <tbody>
              {problemList.map((problemInfo) => {
                  if(isFetched && problemInfo.problemNumber > 30){
                    return (
                      <tr>
                        <td className="px-6 bg-blueGray-50 align-middle border border-solid text-xs whitespace-nowrap p-4">
                          {isFetched ? problemInfo.problemNumber : ""}
                        </td>
                        <td className="px-6 align-middle border border-solid text-xs whitespace-nowrap p-4">
                          {isFetched ? problemInfo.examAnswer : ""}
                        </td>
                        <td className={isFetched && problemInfo.examAnswer===problemInfo.studentAnswer ? 
                          "text-lightBlue-500 px-6 align-middle border border-solid text-xs whitespace-nowrap p-4"
                           : "text-red-500 px-6 align-middle border border-solid text-xs whitespace-nowrap p-4"}>
                          {isFetched ? problemInfo.studentAnswer : ""}
                        </td>
                        <td className="px-6 align-middle border border-solid text-xs whitespace-nowrap p-4">
                          <i className="fas fa-arrow-down text-orange-500 mr-4"></i>
                          {isFetched ? (problemInfo.wrongRate * 100) + "%" : ""}
                        </td>
                      </tr>
                    );
                  }
                  else return null;
                })}
              </tbody>
          
            </table>
            </th>
          </table>
        </div>
      </div>
    </>
  );
}
