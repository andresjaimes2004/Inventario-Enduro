import React, { useState } from "react";
import { Link, useLocation, Navigate, Routes, Route } from "react-router-dom";
import Inicio from "../components/Inicio";
import Productos from "../components/Productos";
import Movimientos from "../components/Movimientos";
import {
  MdMenu,
  MdMenuOpen,
  MdHome,
  MdInventory,
  MdSwapHoriz,
} from "react-icons/md";

const menuItems = [
  {
    label: "Inicio",
    icon: <MdHome className="text-xl md:text-2xl" />,
    href: "/Inicio",
  },
  {
    label: "Productos",
    icon: <MdInventory className="text-xl md:text-2xl" />,
    href: "/Productos",
  },
  {
    label: "Movimientos",
    icon: <MdSwapHoriz className="text-xl md:text-2xl" />,
    href: "/Movimientos",
  },
];

const DashBoard = () => {
  const [isOpen, setIsOpen] = useState(false);
  const location = useLocation();

  return (
    <>
      <div className="flex flex-col md:flex-row min-h-screen">
        {/* Header */}
        <header className="w-full md:w-[240px] h-[80px] md:h-screen bg-[#FF7700] flex p-4 md:p-0">
          <div className="w-full flex md:flex-col items-center md:items-center justify-between md:justify-start md:gap-y-20">
            <img
              src="src/assets/Logo.png"
              alt="Logo Enduro"
              className="w-[100px] md:w-[150px] h-auto mt-0 md:mt-10"
            />
            <div className="flex md:w-full md:items-center">
              <button
                className="block text-black cursor-pointer md:hidden"
                onClick={() => setIsOpen(!isOpen)}
              >
                {isOpen ? (
                  <MdMenuOpen className="text-3xl" />
                ) : (
                  <MdMenu className="text-3xl" />
                )}
              </button>
              <ul
                className={`md:flex md:flex-col md:space-y-4 md:items-center absolute md:static top-20 left-0 w-full bg-[#dff1ff] md:bg-transparent 
                transition-all duration-300 ease-in-out ${
                  isOpen ? "h-[125px]" : "h-[0px]"
                }`}
              >
                {menuItems.map((item) => (
                  <li key={item.label} className="w-full">
                    <Link
                      to={item.href}
                      className={`flex items-center ${
                        isOpen ? "" : "max-md:hidden"
                      } transition-all duration-300 ease-in-out pr-4 pl-4 pt-2 pb-2 md:w-[200px] md:rounded-xl md:mx-4 md:p-3 gap-8 md:text-left ${
                        location.pathname === item.href
                          ? "md:bg-white md:text-black md:font-bold"
                          : "md:bg-gray-300 md:text-black md:font-semibold hover:text-black hover:bg-[#e6e6e6]"
                      }`}
                    >
                      {item.icon}
                      <span className="flex-1">{item.label}</span>
                    </Link>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </header>
        {/* Header End*/}

        {/* Main Content */}
        <main className="flex-1 bg-[#e6e6e6]">
          <Routes>
            <Route path="/" element={<Navigate to="/Inicio" replace />} />
            <Route path="/Inicio" element={<Inicio />} />
            <Route path="/Productos" element={<Productos />} />
            <Route path="/Movimientos" element={<Movimientos />} />
          </Routes>
        </main>
        {/* Main Content End*/}
      </div>
    </>
  );
};

export default DashBoard;
