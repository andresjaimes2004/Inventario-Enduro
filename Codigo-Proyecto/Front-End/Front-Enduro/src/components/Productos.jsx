import { useNavigate } from "react-router-dom";

const Productos = () => {
  const navigate = useNavigate();

  const createProduct = (e) => {
    e.preventDefault();
    navigate("/CrearProducto");
  };

  return (
    <div className="w-full md:min-h-screen flex items-start justify-center px-2 py-5 gap-6">
      <div className="w-full max-w-5xl mx-auto flex flex-col gap-8">
        {/* Encabezado de Productos */}
        <div className="w-full flex md:flex-row min-h-[100px] md:min-h-[100px] items-center justify-center px-4 border-b-2 border-dashed">
          <div className="w-1/2 flex items-start justify-start">
            <h2 className="text-4xl font-bold">LISTA DE PRODUCTOS</h2>
          </div>
          <div className="w-1/2 flex items-end justify-end">
            <button
              className="bg-[#FF7700] cursor-pointer text-xl font-bold text-white p-4 w-[250px] h-[70px] flex items-center justify-center rounded-2xl"
              onClick={createProduct}
            >
              AÑADIR NUEVO PRODUCTO
            </button>
          </div>
        </div>
        {/* Encabezado de Productos End */}

        {/* Barra de Busqueda */}
        <div className="flex w-full md:flex-row items-center justify-center">
          <input
            type="text"
            className="w-full h-[50px] p-2 bg-white rounded-2xl"
            placeholder="Buscar producto"
          />
        </div>
        {/* Barra de Busqueda End */}

        {/* Tabla de Productos */}

        {/* Tabla de Productos End */}
      </div>
    </div>
  );
};

export default Productos;
