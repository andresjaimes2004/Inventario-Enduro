import React, { useState, useEffect } from "react";
import {
  MdMenu,
  MdMenuOpen,
  MdHome,
  MdInventory,
  MdSwapHoriz,
} from "react-icons/md";

const DashBoard = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [active, setActive] = useState("Inicio");

  useEffect(() => {
    setActive("Inicio");
  }, []);

  const menuItems = [
    { label: "Inicio", icon: <MdHome className="text-xl md:text-2xl mr-4" /> },
    {
      label: "Productos",
      icon: <MdInventory className="text-xl md:text-2xl mr-4" />,
    },
    {
      label: "Movimientos",
      icon: <MdSwapHoriz className="text-xl md:text-2xl mr-4" />,
    },
  ];

  return (
    <>
      <div className="flex flex-col md:flex-row min-h-screen">
        {/* Header */}
        <header className="w-full md:w-[240px] h-[80px] md:h-screen bg-[#FF7700] flex p-4 md:p-0">
          <div className="w-full flex md:flex-col items-center md:items-center justify-between md:justify-start md:gap-y-15">
            <img
              src="src/assets/Logo.png"
              alt="Logo Enduro"
              className="w-[100px] md:w-[150px] h-auto mt-0 md:mt-8"
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
                className={`md:flex md:flex-col md:space-y-4 md:items-center absolute md:static top-20 left-0 w-full bg-[#f0f8ff] md:bg-transparent 
                  transition-all duration-300 ease-in-out ${
                    isOpen ? "h-[125px]" : "h-[0px]"
                  }`}
              >
                {menuItems.map((item) => (
                  <li key={item.label}>
                    <a
                      href="#"
                      onClick={() => setActive(item.label)}
                      className={`flex items-center ${
                        isOpen ? "" : "max-md:hidden"
                      } transition-all duration-300 ease-in-out pr-4 pl-4 pt-2 pb-2 md:w-[190px] md:rounded-xl md:mx-4 md:p-3 md:text-center ${
                        active === item.label
                          ? "md:bg-white md:text-black md:font-bold"
                          : "md:bg-[#CACACA] md:text-black md:font-semibold hover:text-black hover:bg-[#e6e6e6]"
                      }`}
                    >
                      {item.icon}
                      <span>{item.label}</span>
                    </a>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </header>
        {/* Header End*/}

        {/* Main Content */}
        <main className="flex-1 bg-white p-4">
          <h1 className="text-2xl font-bold">Bienvenido al Dashboard</h1>
        </main>
        {/* Main Content End*/}
      </div>
    </>
  );
};

export default DashBoard;
