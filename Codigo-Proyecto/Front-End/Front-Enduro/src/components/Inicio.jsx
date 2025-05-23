import React from "react";
import { useNavigate } from "react-router-dom";

const Inicio = () => {
  const navigate = useNavigate();

  return (
    <div className="w-full md:min-h-screen flex items-center justify-center px-2 py-8">
      <div className="w-full max-w-5xl mx-auto flex flex-col gap-2">
        {/* Primer div: ancho completo */}
        <div
          className="w-full flex md:flex-row bg-[#AFCBD3] rounded-xl shadow-md min-h-[200px] md:min-h-[300px] 
        items-center justify-center"
        >
          <div className="w-1/2 flex items-center justify-center">
            <img
              src="src/assets/homestock.webp"
              alt="Imagen principal"
              className="flex-shrink-0 w-[120px] h-[120px] md:w-[300px] md:h-auto object-contain "
              style={{ maxWidth: "30vw", maxHeight: "30vw" }}
            />
          </div>
          <div className="w-1/2 flex-1 min-w-0 px-2 md:px-4 text-center">
            <h2 className="text-xl md:text-3xl font-bold mb-2 break-words">
              ZAPATOS ENDURO
            </h2>
            <p className=" text-black text-sm  md:text-base break-words overflow-hidden">
              Estilo, comodidad, individualidad, calidad, pasión y
              empoderamiento.
            </p>
          </div>
        </div>

        {/* Dos divs debajo, 50% cada uno en desktop, 100% en móvil */}
        <div className="w-full flex flex-col md:flex-row gap-2">
          {/* Segundo div */}
          <div
            className="flex md:flex-row bg-[#AFCBD3] rounded-xl shadow-md min-h-[140px] md:min-h-[280px] 
            w-full md:w-1/2 items-center p-4 md:p-2 cursor-pointer hover:shadow-lg transition-shadow"
            onClick={() => navigate("/Productos")}
          >
            <div className="w-1/2 flex items-center justify-center">
              <img
                src="src/assets/box.webp"
                alt="Imagen secundaria 1"
                className="flex-shrink-0 w-[90px] h-[90px] md:w-full md:h-auto object-contain "
                style={{ maxWidth: "30vw", maxHeight: "30vw" }}
              />
            </div>
            <div className="w-1/2 flex-1 min-w-0 px-1 md:px-2 text-center">
              <h3 className="text-xl md:text-3xl text-[#6296A5] font-bold md:font-bold mb-5 break-words">
                Total Productos
              </h3>
              <p className="text-black text-4xl md:text-4xl font-bold break-words overflow-hidden">
                8
              </p>
            </div>
          </div>
          {/* Tercer div */}
          <div
            className="flex md:flex-row bg-[#AFCBD3] rounded-xl shadow-md min-h-[140px] md:min-h-[280px] 
            w-full md:w-1/2 items-center p-4 md:p-2 cursor-pointer hover:shadow-lg transition-shadow"
            onClick={() => navigate("/Movimientos")}
          >
            <div className="w-1/2 flex items-center justify-center">
              <img
                src="src/assets/marketing.svg"
                alt="Imagen secundaria 2"
                className="flex-shrink-0 w-[90px] h-[90px] md:w-full md:h-[250px] object-contain "
                style={{ maxWidth: "30vw", maxHeight: "30vw" }}
              />
            </div>
            <div className="w-1/2 flex-1 min-w-0 px-1 md:px-2 text-center ">
              <h3 className="text-xl md:text-3xl text-[#6296A5] font-bold md:font-bold mb-5 break-words">
                Total Movimientos
              </h3>
              <p className="text-black text-4xl md:text-4xl font-bold break-words overflow-hidden">
                20
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Inicio;
