import React from "react";

export default function StudentAnswerTr(props) {
    return (
        <tr>
            <td className="border-t-0 px-6 align-middle border border-solid text-xs whitespace-nowrap p-4 text-left flex items-center">
                  <span
                      className={
                          "ml-3 font-bold blueGray"
                      }
                  >
                    {props.number}번
                  </span>
            </td>
            <td className="border-blueGray-100 text-xs uppercase font-semibold text-center border border-solid"
            >
                <div
                    className="inline-flex rounded-md shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none1 focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
                >
                    <input
                        type="radio"
                        className="hidden "
                        name={"radio"+props.number}
                        id={"radio"+props.number+"_1"}
                        value="1"
                    />
                    <label className="inline-block rounded-l bg-primary-100 px-7 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-black transition duration-1501 ease-in-out hover:bg-success-600 focus:bg-success-600 focus:outline-none focus:ring-0 active:bg-success-700 " htmlFor={"radio"+props.number+"_1"}>1</label>
                    <input
                        type="radio"
                        className="hidden "
                        name={"radio"+props.number}
                        id={"radio"+props.number+"_2"}
                        value="2"
                    />
                    <label className="inline-block bg-primary-100 px-7 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-black transition duration-1501 ease-in-out hover:bg-success-600 focus:bg-success-600 focus:outline-none focus:ring-0 active:bg-success-700" htmlFor={"radio"+props.number+"_2"}>2</label>
                    <input
                        type="radio"
                        className="hidden "
                        name={"radio"+props.number}
                        id={"radio"+props.number+"_3"}
                        value="3"
                    />
                    <label className="inline-block bg-primary-100 px-7 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-black transition duration-1501 ease-in-out hover:bg-success-600 focus:bg-success-600 focus:outline-none focus:ring-0 active:bg-success-700" htmlFor={"radio"+props.number+"_3"}>3</label>
                    <input
                        type="radio"
                        className="hidden "
                        name={"radio"+props.number}
                        id={"radio"+props.number+"_4"}
                        value="4"
                    />
                    <label className="inline-block bg-primary-100 px-7 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-black transition duration-1501 ease-in-out hover:bg-success-600 focus:bg-success-600 focus:outline-none focus:ring-0 active:bg-success-700" htmlFor={"radio"+props.number+"_4"}>4</label>
                    <input
                        type="radio"
                        name={"radio"+props.number}
                        className="hidden "
                        id={"radio"+props.number+"_5"}
                        value="5"
                    />
                    <label className=" inline-block rounded-r bg-primary-100 px-7 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-black transition duration-1501 ease-in-out hover:bg-success-600 focus:bg-success-600 focus:outline-none focus:ring-0 active:bg-success-700" htmlFor={"radio"+props.number+"_5"}>5</label>
                </div>
            </td>
        </tr>
    );
}