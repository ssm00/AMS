import React, {useEffect, useState} from "react";
import {call} from "../../../service/ApiService";

// components

export default function TeacherTopWrongRate({examNumber}) {
  const [data, setData] = useState([]);
  const [isFetched, setIsFetched] = useState(false);
  useEffect(() => {
    call("/ranking/wrong-rate", "POST", {"examNumber": examNumber ,"grade" : 3, "examSubject" : "ENGLISH"}).then((response) => {
      setData(response.result.wrongRateInfoList);
      setIsFetched(true);
    });
  },[isFetched,examNumber]);
  return (
    <>
      <div className="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-lg rounded">
        <div className="rounded-t mb-0 px-4 py-3 border-0">
          <div className="flex flex-wrap items-center">
            <div className="relative w-full px-4 max-w-full flex-grow flex-1">
              <h3 className="font-semibold text-base text-blueGray-700">
                {examNumber}회차 오답률 순위
              </h3>
            </div>
            <div className="relative w-full px-4 max-w-full flex-grow flex-1 text-right">
              <button
                className="bg-indigo-500 text-white active:bg-indigo-600 text-xs font-bold uppercase px-3 py-1 rounded outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                type="button"
              >
                모두 보기
              </button>
            </div>
          </div>
        </div>
        <div className="block w-full overflow-x-auto">
          {/* Projects table */}
          <table className="items-center w-full bg-transparent border-collapse">
            <thead>
              <tr>
                <th rowSpan={2} className=" px-6 bg-blueGray-50 text-blueGray-500 align-middle border border-solid border-blueGray-100 py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-center">
                  순위
                </th>
                <th rowSpan={2} className="px-6 bg-blueGray-50 text-blueGray-500 align-middle border border-solid border-blueGray-100 py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-center">
                  문항 번호
                </th>
                <th rowSpan={2} className="px-6 bg-blueGray-50 text-blueGray-500 align-middle border border-solid border-blueGray-100 py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-center">
                  오답률
                </th>
                <th rowSpan={2} className="px-6 bg-blueGray-50 text-blueGray-500 align-middle border border-solid border-blueGray-100 py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-center">
                  정답
                </th>
                <th colSpan={5} className="px-6 bg-blueGray-50 text-blueGray-500 align-middle border border-solid border-blueGray-200 py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-center">
                  선택지별 비율
                </th>
              </tr>
              <tr>
                <th className="px-4 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  1
                </th>
                <th className="px-4 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  2
                </th>
                <th className="px-4 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  3
                </th>
                <th className="px-4 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  4
                </th>
                <th className="px-4 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  5
                </th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4 text-left">
                  1
                </th>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  {isFetched ? data[0].problemNumber+" 번" : ""}
                </td>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  {isFetched ? data[0].wrongRate+"%" : ""}
                </td>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  <i className="fas fa-arrow-up text-emerald-500 mr-4"></i>
                  {isFetched ? data[0].correctAnswer : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[0].firstRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[0].secondRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[0].thirdRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[0].fourthRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[0].fifthRate : ""}
                </td>
              </tr>
              <tr>
                <th className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4 text-left">
                  2
                </th>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  {isFetched ? data[1].problemNumber+" 번" : ""}
                </td>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  {isFetched ? data[1].wrongRate+"%" : ""}
                </td>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  <i className="fas fa-arrow-up text-emerald-500 mr-4"></i>
                  {isFetched ? data[1].correctAnswer : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[1].firstRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[1].secondRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[1].thirdRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[1].fourthRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[1].fifthRate : ""}
                </td>
              </tr>
              <tr>
                <th className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4 text-left">
                  2
                </th>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  {isFetched ? data[2].problemNumber+" 번" : ""}
                </td>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  {isFetched ? data[2].wrongRate+"%" : ""}
                </td>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  <i className="fas fa-arrow-up text-emerald-500 mr-4"></i>
                  {isFetched ? data[2].correctAnswer : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[2].firstRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[2].secondRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[2].thirdRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[2].fourthRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[2].fifthRate : ""}
                </td>
              </tr>
              <tr>
                <th className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4 text-left">
                  4
                </th>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  {isFetched ? data[3].problemNumber+" 번" : ""}
                </td>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  {isFetched ? data[3].wrongRate+"%" : ""}
                </td>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  <i className="fas fa-arrow-up text-emerald-500 mr-4"></i>
                  {isFetched ? data[3].correctAnswer : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[3].firstRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[3].secondRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[3].thirdRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[3].fourthRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[3].fifthRate : ""}
                </td>
              </tr>
              <tr>
                <th className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4 text-left">
                  5
                </th>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  {isFetched ? data[4].problemNumber+" 번" : ""}
                </td>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  {isFetched ? data[4].wrongRate+"%" : ""}
                </td>
                <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                  <i className="fas fa-arrow-up text-emerald-500 mr-4"></i>
                  {isFetched ? data[4].correctAnswer : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[4].firstRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[4].secondRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[4].thirdRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[4].fourthRate : ""}
                </td>
                <td className="px-2 align-middle bg-blueGray-50 text-blueGray-500 border border-solid border-blueGray-300 py-2 text-xs uppercase font-semibold text-center">
                  {isFetched ? data[4].fifthRate : ""}
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
}
