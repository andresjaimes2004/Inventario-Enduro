import React, { useState, useRef } from "react";
import { AiOutlineDown } from "react-icons/ai";

const TALLAS = Array.from({ length: 18 }, (_, i) => (27 + i).toString());

const CrearProducto = () => {
  const [selectedSizes, setSelectedSizes] = useState([]);
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const inputRef = useRef(null);

  // Cierra el dropdown si se hace click fuera
  React.useEffect(() => {
    const handleClickOutside = (event) => {
      if (inputRef.current && !inputRef.current.contains(event.target)) {
        setDropdownOpen(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  const handleSelectSize = (size) => {
    setSelectedSizes((prev) =>
      prev.includes(size) ? prev.filter((s) => s !== size) : [...prev, size]
    );
  };

  return (
    <div className="w-full md:min-h-screen flex items-start justify-center px-2 py-8">
      <div className="w-full max-w-5xl mx-auto flex flex-col gap-2">
        <div className="w-full flex md:flex-row bg-[#AFCBD3] rounded-xl shadow-md min-h-[200px] max-h-[500px] md:min-h-[400px] md:max-h-[600px] items-center justify-center px-10 py-10 gap-8">
          <div className="w-[40%] flex flex-col justify-between h-full">
            <div className="w-full flex flex-col items-center justify-center gap-8">
              <h2 className="w-full text-2xl font-bold text-center">
                NUEVO PRODUCTO
              </h2>
              <div className="w-full items-center justify-center">
                <div className="items-start justify-start">
                  <h2 className="text-xl font-semibold">Nombre</h2>
                  <input
                    type="text"
                    placeholder="Escribe el nombre del producto"
                    className="text-start text-md text-black bg-white rounded-2xl w-full h-[40px] p-4"
                  />
                </div>
                <div className="items-start justify-start">
                  <h2 className="text-xl font-semibold mb-2">Tallas</h2>
                  <div className="relative w-full" ref={inputRef}>
                    <div
                      className="flex items-center bg-white rounded-2xl w-full h-[45px] p-4 pr-10 cursor-pointer border border-gray-300"
                      onClick={() => setDropdownOpen((open) => !open)}
                    >
                      <span
                        className={`text-md text-black ${
                          selectedSizes.length === 0 ? "text-gray-400" : ""
                        }`}
                      >
                        {selectedSizes.length === 0
                          ? "Selecciona las tallas"
                          : selectedSizes.join(", ")}
                      </span>
                      <AiOutlineDown
                        className="absolute right-4 top-1/2 transform -translate-y-1/2 text-gray-500 pointer-events-none"
                        size={20}
                      />
                    </div>
                    {dropdownOpen && (
                      <div className="absolute left-0 top-full mt-2 w-full bg-white rounded-xl shadow-lg z-10 max-h-40 overflow-y-auto transition-all duration-200">
                        {TALLAS.map((size) => (
                          <div
                            key={size}
                            className={`px-4 py-2 cursor-pointer hover:bg-[#AFCBD3] flex items-center gap-2 ${
                              selectedSizes.includes(size)
                                ? "font-bold text-[#FF7700]"
                                : "text-black"
                            }`}
                            onClick={() => handleSelectSize(size)}
                          >
                            <input
                              type="checkbox"
                              checked={selectedSizes.includes(size)}
                              readOnly
                              className="accent-[#FF7700]"
                            />
                            <span>{size}</span>
                          </div>
                        ))}
                      </div>
                    )}
                  </div>
                </div>
              </div>
            </div>
            <button className="bg-[#FF7700] cursor-pointer text-white items-center justify-center w-full h-[45px] rounded-2xl flex ">
              CREAR PRODUCTO
            </button>
          </div>
          <div className="w-[60%] flex items-center justify-center h-full">
            <div className="w-full aspect-square bg-white rounded-2xl flex flex-col items-center justify-center relative">
              <input
                type="file"
                accept="image/*"
                className="absolute inset-0 w-full h-full opacity-0 cursor-pointer z-10"
              />
              <div className="flex flex-col items-center justify-center z-0">
                <span className="text-gray-500 text-center mb-2">
                  Arrastra tu imagen aquí o
                </span>
                <button
                  type="button"
                  className="bg-[#FF7700] text-white rounded-lg px-4 py-2 font-semibold shadow hover:bg-[#e06600] transition"
                  onClick={() => {
                    // Dispara el input file al hacer click en el botón
                    const input = document.querySelector('input[type="file"]');
                    if (input) input.click();
                  }}
                >
                  Dale a este botón
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CrearProducto;
