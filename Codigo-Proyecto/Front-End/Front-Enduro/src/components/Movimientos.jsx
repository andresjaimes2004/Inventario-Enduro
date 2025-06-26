import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Movimientos = () => {
  // Estado para manejar la búsqueda
  const [searchTerm, setSearchTerm] = useState("");

  // Estado para movimientos - se guarda en memoria durante la sesión
  const [movimientos, setMovimientos] = useState([
    {
      id: 1,
      titulo: "PRODUCTO DE EJEMPLO 1",
      fecha: "11:10 AM 28/03/2023",
      razon: "VENTA DE PRODUCTO",
      habian: 10,
      hay: 9,
      tipo: "Salida",
      productId: 1,
    },
    {
      id: 2,
      titulo: "EJEMPLO 2",
      fecha: "11:10 AM 29/03/2023",
      razon: "INGRESO DE INVENTARIO",
      habian: 9,
      hay: 17,
      tipo: "Entrada",
      productId: 2,
    },
    {
      id: 3,
      titulo: "PRODUCTO DE EJEMPLO 3",
      fecha: "11:10 AM 29/03/2023",
      razon: "DEVOLUCIÓN",
      habian: 17,
      hay: 18,
      tipo: "Entrada",
      productId: 3,
    },
    {
      id: 4,
      titulo: "EJEMPLO 4",
      fecha: "11:10 AM 29/03/2023",
      razon: "VENTA DE PRODUCTO",
      habian: 10,
      hay: 9,
      tipo: "Salida",
      productId: 4,
    },
    {
      id: 5,
      titulo: "PRODUCTO DE EJEMPLO 5",
      fecha: "11:10 AM 29/03/2023",
      razon: "VENTA DE PRODUCTO",
      habian: 10,
      hay: 9,
      tipo: "Salida",
      productId: 5,
    },
    {
      id: 6,
      titulo: "EJEMPLO 6",
      fecha: "11:10 AM 29/03/2023",
      razon: "VENTA DE PRODUCTO",
      habian: 10,
      hay: 9,
      tipo: "Salida",
      productId: 6,
    },
    {
      id: 7,
      titulo: "PRODUCTO DE EJEMPLO 7",
      fecha: "11:10 AM 29/03/2023",
      razon: "VENTA DE PRODUCTO",
      habian: 10,
      hay: 9,
      tipo: "Salida",
      productId: 7,
    },
  ]);

  // Función para agregar un nuevo movimiento (para uso futuro)
  const addMovement = (newMovement) => {
    setMovimientos((prev) => [...prev, { ...newMovement, id: Date.now() }]);
  };

  const navigate = useNavigate();

  const createMovement = (e) => {
    e.preventDefault();
    navigate("/AnadirMovimiento");
  };

  // Filtrar movimientos basado en la búsqueda
  const movimientosFiltrados = movimientos.filter(
    (movimiento) =>
      movimiento.titulo.toLowerCase().includes(searchTerm.toLowerCase()) ||
      movimiento.razon.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };

  const getTipoColor = (tipo) => {
    switch (tipo) {
      case "Entrada":
        return "bg-green-500";
      case "Salida":
        return "bg-red-500";
      default:
        return "bg-orange-500";
    }
  };

  return (
    <div className="w-full md:min-h-screen flex items-start justify-center px-2 py-5 gap-6">
      <div className="w-full max-w-5xl mx-auto flex flex-col gap-8">
        {/* Encabezado de Movimientos */}
        <div className="w-full flex md:flex-row min-h-[100px] md:min-h-[100px] items-center justify-center px-4 border-b-2 border-dashed">
          <div className="w-1/2 flex items-start justify-start">
            <h2 className="text-4xl font-bold">LISTA DE MOVIMIENTOS</h2>
          </div>
          <div className="w-1/2 flex items-end justify-end">
            <button
              className="bg-[#FF7700] cursor-pointer text-xl font-bold text-white p-4 w-[250px] h-[70px] flex items-center justify-center rounded-2xl hover:bg-[#e06600] transition-colors"
              onClick={createMovement}
            >
              AÑADIR NUEVO MOVIMIENTO
            </button>
          </div>
        </div>
        {/* Encabezado de Movimientos End */}

        {/* Barra de Búsqueda */}
        <div className="flex w-full md:flex-row items-center justify-center">
          <input
            type="text"
            className="w-full h-[50px] p-4 bg-white rounded-2xl border border-gray-300 focus:outline-none focus:ring-2 focus:ring-[#FF7700] focus:border-transparent"
            placeholder="Buscar movimiento o razón"
            value={searchTerm}
            onChange={handleSearchChange}
          />
        </div>
        {/* Barra de Búsqueda End */}

        {/* Lista de Movimientos */}
        <div className="w-full flex flex-col gap-4">
          {movimientosFiltrados.length > 0 ? (
            movimientosFiltrados.map((movimiento) => (
              <div
                key={movimiento.id}
                className="w-full bg-[#AFCBD3] rounded-xl shadow-md p-4 flex items-center justify-between"
              >
                {/* Lado izquierdo - Icono y información del movimiento */}
                <div className="flex items-center gap-4 flex-1">
                  {/* Icono del movimiento */}
                  <div className="w-12 h-12 bg-gray-700 rounded-lg flex items-center justify-center">
                    <svg
                      className="w-8 h-8 text-white"
                      fill="currentColor"
                      viewBox="0 0 24 24"
                    >
                      <path d="M19 7h-3V6a4 4 0 0 0-8 0v1H5a1 1 0 0 0-1 1v11a3 3 0 0 0 3 3h10a3 3 0 0 0 3-3V8a1 1 0 0 0-1-1zM10 6a2 2 0 0 1 4 0v1h-4V6zm8 13a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V9h2v1a1 1 0 0 0 2 0V9h4v1a1 1 0 0 0 2 0V9h2v10z" />
                    </svg>
                  </div>

                  {/* Información del movimiento */}
                  <div className="flex-1">
                    <h3 className="text-lg font-bold text-gray-800">
                      {movimiento.titulo}
                    </h3>
                    <p className="text-sm text-gray-600">{movimiento.fecha}</p>
                  </div>
                </div>

                {/* Columna RAZÓN */}
                <div className="flex flex-col items-center justify-center px-4 min-w-[150px]">
                  <p className="text-xs font-bold text-gray-600 mb-1">RAZÓN</p>
                  <p className="text-sm text-gray-800 text-center">
                    {movimiento.razon}
                  </p>
                </div>

                {/* Columna HABÍAN */}
                <div className="flex flex-col items-center justify-center px-4 min-w-[80px]">
                  <p className="text-xs font-bold text-gray-600 mb-1">HABÍAN</p>
                  <p className="text-lg font-bold text-gray-800">
                    {movimiento.habian}
                  </p>
                </div>

                {/* Columna HAY */}
                <div className="flex flex-col items-center justify-center px-4 min-w-[80px]">
                  <p className="text-xs font-bold text-gray-600 mb-1">HAY</p>
                  <p className="text-lg font-bold text-gray-800">
                    {movimiento.hay}
                  </p>
                </div>

                {/* Columna Tipo */}
                <div className="flex flex-col items-center justify-center px-4 min-w-[100px]">
                  <p className="text-xs font-bold text-gray-600 mb-1">Tipo</p>
                  <span
                    className={`px-3 py-1 rounded-full text-xs font-bold text-white ${getTipoColor(
                      movimiento.tipo
                    )}`}
                  >
                    {movimiento.tipo}
                  </span>
                </div>
              </div>
            ))
          ) : (
            <div className="w-full bg-[#AFCBD3] rounded-xl shadow-md p-8 text-center">
              <p className="text-lg text-gray-700">
                {searchTerm
                  ? "No se encontraron movimientos que coincidan con la búsqueda."
                  : "No hay movimientos disponibles."}
              </p>
              {!searchTerm && (
                <button
                  onClick={createMovement}
                  className="mt-4 bg-[#FF7700] hover:bg-[#e06600] text-white px-6 py-2 rounded-lg font-medium transition-colors cursor-pointer"
                >
                  Crear tu primer movimiento
                </button>
              )}
            </div>
          )}
        </div>
        {/* Lista de Movimientos End */}
      </div>
    </div>
  );
};

export default Movimientos;
