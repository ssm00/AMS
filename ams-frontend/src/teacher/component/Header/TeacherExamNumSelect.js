import React, {Fragment, useEffect, useState} from "react";
import PropTypes from "prop-types";
import Select from "react-select";
import { Listbox, Transition } from '@headlessui/react'
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/react/20/solid'
import TeacherEngStat from "./TeacherEngStat";



function classNames(...classes) {
  return classes.filter(Boolean).join(' ')
}


export default function TeacherExamNumSelect({
  options,
    changeExamNumber,
}) {
  const people = [
    {
      id: 1,
      name: 'Wade Cooper',
      avatar:
        'https://images.unsplash.com/photo-1491528323818-fdd1faba62cc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80',
    },
    {
      id: 2,
      name: 'Arlene Mccoy',
      avatar:
        'https://images.unsplash.com/photo-1550525811-e5869dd03032?ixlib=rb-1.2.1&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80',
    },
  ]
  const [selected, setSelected] = useState(people[0])


  return (
      <>
        {/* Header */}
        <label id="listbox-label" className="block text-sm font-medium leading-6 text-gray-900">Assigned to</label>
        <div>
          <button type="button"
                  className="relative w-full cursor-default rounded-md bg-white py-1.5 pl-3 pr-10 text-left text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-500 sm:text-sm sm:leading-6"
                  aria-haspopup="listbox" aria-expanded="true" aria-labelledby="listbox-label">
            <span className="flex items-center">
              <img
                  src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                  alt="" className="h-5 w-5 flex-shrink-0 rounded-full"/>
            </span>
          </button>
        </div>
      </>
  );
}

TeacherExamNumSelect.defaultProps = {
  options: [
    { value: '1', label: '1회차' },
    { value: '2', label: '2회차' },
    { value: '3', label: '3회차' },
    { value: '4', label: '4회차'},
  ],
};

TeacherExamNumSelect.propTypes = {
  statSubtitle: PropTypes.array,
};
