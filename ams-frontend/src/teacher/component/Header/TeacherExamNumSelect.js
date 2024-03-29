import React, {Fragment, useEffect, useState} from "react";

import { Listbox, Transition } from '@headlessui/react'
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/react/20/solid'
import {call} from "../../../service/ApiService";



function classNames(...classes) {
  return classes.filter(Boolean).join(' ')
}


export default function TeacherExamNumSelect({
  options,
    changeExamNumber,
}) {
  const examNumber = [
    {
        id: 1,
        name: "1회차"
    },
  ]
  const [selected, setSelected] = useState(examNumber[0]);
  call("/teachers/average-graph", "POST", {"grade" : 3, "examSubject" : "ENGLISH"}).then((response) => {
    for(let i=2; i<=response.result.eachAverageScoreList.length; i++){
      examNumber.push({
        id: i,
        name: i+"회차"
      })
    }
  }).catch((error) => {
    return;
  });
  React.useEffect(() => {
    changeExamNumber(selected.id);
  },[selected])
  return (
      <Listbox value={selected} onChange={setSelected}>
        {({open}) => (
            <>
              <div className="relative flex flex-col min-w-0 break-words bg-white rounded mb-6 xl:mb-0 shadow-lg">
                <div className="flex-auto p-4">
                  <div className="relative w-full pr-4 max-w-full flex-grow flex-1 text-center">

                    <div className="relative mt-2">
                      <Listbox.Button
                          className="relative w-full h-4/5 cursor-default rounded-md bg-white py-1.5 pl-3 pr-10 text-left text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:outline-none focus:ring-1 focus:ring-indigo-500 sm:text-sm sm:leading-6">
                        <span className="flex items-center">
                          <span className="ml-3 block truncate font-semibold text-xl text-blueGray-700">{selected.name}</span>
                        </span>
                        <span className="pointer-events-none absolute inset-y-0 right-0 top-0 ml-3 flex-row items-center pr-2">
                          <ChevronUpDownIcon className="h-5 w-5 text-gray-400" aria-hidden="true"/>
                        </span>
                      </Listbox.Button>
                      <Transition
                          show={open}
                          as={Fragment}
                          leave="transition ease-in duration-100"
                          leaveFrom="opacity-100"
                          leaveTo="opacity-0"
                      >
                        <Listbox.Options
                            className="absolute z-10 mt-1 max-h-56 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm">
                          {examNumber.map((examNumber) => (
                              <Listbox.Option
                                  key={examNumber.id}
                                  className={({active}) =>
                                      classNames(
                                          active ? 'bg-indigo-600 text-white' : 'text-gray-900',
                                          'relative cursor-default select-none py-2 pl-3 pr-9'
                                      )
                                  }
                                  value={examNumber}
                              >
                                {({selected, active}) => (
                                    <>
                                      <div className="flex items-center">
                                        <span
                                            className={classNames(selected ? 'font-semibold' : 'font-normal', 'ml-3 block truncate')}
                                        >
                                          {examNumber.name}
                                        </span>
                                      </div>

                                      {selected ? (
                                          <span
                                              className={classNames(
                                                  active ? 'text-pink-400' : 'text-indigo-600',
                                                  'absolute inset-y-0 right-0 flex items-center pr-4'
                                              )}
                                          >
                                            <CheckIcon className="h-5 w-5" aria-hidden=""/>
                                          </span>
                                      ) : null}
                                    </>
                                )}
                              </Listbox.Option>
                          ))}
                        </Listbox.Options>
                      </Transition>
                    </div>
                  </div>
                </div>
              </div>
            </>
        )}
      </Listbox>
  );
}
