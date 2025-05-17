import React from "react";

const DashBoard = () => {
  return (
    <>
      <div className="flex flex-col md:flex-row min-h-screen">
        {/* Sidebar/Header */}
        <header className="bg-[#FF7700] w-full md:w-[220px] h-[80px] md:h-screen flex items-center md:items-start justify-center md:justify-center px-4 md:px-0 gap-16">
          <img
            src="src/assets/Logo.png"
            alt="Logo Enduro"
            className="w-[100px] md:w-[150px] h-auto mt-0 md:mt-8"
          />
        </header>
        {/* Main Content */}
        <main className="flex-1 bg-white p-4">
          {/* Aquí va el contenido del dashboard */}
          <h1 className="text-2xl font-bold">Bienvenido al Dashboard</h1>
        </main>
      </div>
    </>
  );
};

export default DashBoard;
