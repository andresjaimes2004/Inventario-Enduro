import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

// const TALLAS = Array.from({ length: 18 }, (_, i) => (27 + i).toString());

// const CrearProducto = () => {
//   const [selectedSizes, setSelectedSizes] = useState([]);
//   const [dropdownOpen, setDropdownOpen] = useState(false);
//   const inputRef = useRef(null);

//   // Cierra el dropdown si se hace click fuera
//   React.useEffect(() => {
//     const handleClickOutside = (event) => {
//       if (inputRef.current && !inputRef.current.contains(event.target)) {
//         setDropdownOpen(false);
//       }
//     };
//     document.addEventListener("mousedown", handleClickOutside);
//     return () => document.removeEventListener("mousedown", handleClickOutside);
//   }, []);

//   const handleSelectSize = (size) => {
//     setSelectedSizes((prev) =>
//       prev.includes(size) ? prev.filter((s) => s !== size) : [...prev, size]
//     );
//   };

//   return (
//     <div className="w-full md:min-h-screen flex items-start justify-center px-2 py-8">
//       <div className="w-full max-w-5xl mx-auto flex flex-col gap-2">
//         <div className="w-full flex md:flex-row bg-[#AFCBD3] rounded-xl shadow-md min-h-[200px] max-h-[500px] md:min-h-[400px] md:max-h-[600px] items-center justify-center px-10 py-10 gap-8">
//           <div className="w-[40%] flex flex-col justify-between h-full">
//             <div className="w-full flex flex-col items-center justify-center gap-8">
//               <h2 className="w-full text-2xl font-bold text-center">
//                 NUEVO PRODUCTO
//               </h2>
//               <div className="w-full items-center justify-center">
//                 <div className="items-start justify-start">
//                   <h2 className="text-xl font-semibold">Nombre</h2>
//                   <input
//                     type="text"
//                     placeholder="Escribe el nombre del producto"
//                     className="text-start text-md text-black bg-white rounded-2xl w-full h-[40px] p-4"
//                   />
//                 </div>
//                 <div className="items-start justify-start">
//                   <h2 className="text-xl font-semibold mb-2">Tallas</h2>
//                   <div className="relative w-full" ref={inputRef}>
//                     <div
//                       className="flex items-center bg-white rounded-2xl w-full h-[45px] p-4 pr-10 cursor-pointer border border-gray-300"
//                       onClick={() => setDropdownOpen((open) => !open)}
//                     >
//                       <span
//                         className={`text-md text-black ${
//                           selectedSizes.length === 0 ? "text-gray-400" : ""
//                         }`}
//                       >
//                         {selectedSizes.length === 0
//                           ? "Selecciona las tallas"
//                           : selectedSizes.join(", ")}
//                       </span>
//                       <AiOutlineDown
//                         className="absolute right-4 top-1/2 transform -translate-y-1/2 text-gray-500 pointer-events-none"
//                         size={20}
//                       />
//                     </div>
//                     {dropdownOpen && (
//                       <div className="absolute left-0 top-full mt-2 w-full bg-white rounded-xl shadow-lg z-10 max-h-40 overflow-y-auto transition-all duration-200">
//                         {TALLAS.map((size) => (
//                           <div
//                             key={size}
//                             className={`px-4 py-2 cursor-pointer hover:bg-[#AFCBD3] flex items-center gap-2 ${
//                               selectedSizes.includes(size)
//                                 ? "font-bold text-[#FF7700]"
//                                 : "text-black"
//                             }`}
//                             onClick={() => handleSelectSize(size)}
//                           >
//                             <input
//                               type="checkbox"
//                               checked={selectedSizes.includes(size)}
//                               readOnly
//                               className="accent-[#FF7700]"
//                             />
//                             <span>{size}</span>
//                           </div>
//                         ))}
//                       </div>
//                     )}
//                   </div>
//                 </div>
//               </div>
//             </div>
//             <button className="bg-[#FF7700] cursor-pointer text-white items-center justify-center w-full h-[45px] rounded-2xl flex ">
//               CREAR PRODUCTO
//             </button>
//           </div>
//           <div className="w-[60%] flex items-center justify-center h-full">
//             <div className="w-full aspect-square bg-white rounded-2xl flex flex-col items-center justify-center relative">
//               <input
//                 type="file"
//                 accept="image/*"
//                 className="absolute inset-0 w-full h-full opacity-0 cursor-pointer z-10"
//               />
//               <div className="flex flex-col items-center justify-center z-0">
//                 <span className="text-gray-500 text-center mb-2">
//                   Arrastra tu imagen aquí o
//                 </span>
//                 <button
//                   type="button"
//                   className="bg-[#FF7700] text-white rounded-lg px-4 py-2 font-semibold shadow hover:bg-[#e06600] transition"
//                   onClick={() => {
//                     // Dispara el input file al hacer click en el botón
//                     const input = document.querySelector('input[type="file"]');
//                     if (input) input.click();
//                   }}
//                 >
//                   Dale a este botón
//                 </button>
//               </div>
//             </div>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default CrearProducto;

const CrearProducto = () => {
  const [productData, setProductData] = useState({
    nombre: "",
    tallas: [],
    precio: "",
  });
  const [selectedImage, setSelectedImage] = useState(null);
  const [imagePreview, setImagePreview] = useState(null);

  const tallasDisponibles = [35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45];

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProductData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleTallaChange = (talla) => {
    setProductData((prev) => ({
      ...prev,
      tallas: prev.tallas.includes(talla)
        ? prev.tallas.filter((t) => t !== talla)
        : [...prev.tallas, talla].sort((a, b) => a - b),
    }));
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setSelectedImage(file);
      const reader = new FileReader();
      reader.onload = (e) => {
        setImagePreview(e.target.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const handleDrop = (e) => {
    e.preventDefault();
    const file = e.dataTransfer.files[0];
    if (file && file.type.startsWith("image/")) {
      setSelectedImage(file);
      const reader = new FileReader();
      reader.onload = (e) => {
        setImagePreview(e.target.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Producto a crear:", productData);
    console.log("Imagen:", selectedImage);
    // Aquí iría la lógica para enviar los datos
    alert("Producto creado exitosamente (demo)");
  };

  const navigate = useNavigate(); // Hook para navegar

  const goBack = () => {
    // Función para volver atrás
    navigate("/Productos");
  };

  return (
    <div className="w-full min-h-screen bg-gray-50 flex flex-col">
      <div className="flex-1 flex flex-col max-w-7xl mx-auto w-full py-4">
        {/* Header */}
        <div className="flex-shrink-0 px-4 pb-2">
          <button
            onClick={goBack}
            className="mb-2 text-orange-400 hover:text-orange-500 cursor-pointer font-medium flex items-center gap-2"
          >
            ← Volver a Productos
          </button>
          <h1 className="text-2xl font-bold text-gray-900">
            Crear Nuevo Producto
          </h1>
        </div>

        {/* Contenedor Principal - Responsive */}
        <div className="flex flex-col lg:flex-row gap-4 px-4 pb-4">
          {/* Sección Formulario - Responsive */}
          <div className="w-full lg:w-2/5 bg-[#AFCBD3] rounded-xl shadow-lg flex flex-col min-h-[500px]">
            <div className="flex-shrink-0 p-4">
              <h2 className="text-lg font-semibold text-gray-800">
                Información del Producto
              </h2>
            </div>

            <div className="flex-1 flex flex-col p-4 pt-5">
              {/* Contenido del formulario */}
              <div className="flex-1 space-y-8">
                {/* Nombre del Producto */}
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-1">
                    Nombre del Producto
                  </label>
                  <input
                    type="text"
                    name="nombre"
                    value={productData.nombre}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 bg-white border-0 rounded-2xl"
                    placeholder="Ej: Nike Air Max 270"
                    required
                  />
                </div>

                {/* Tallas Disponibles */}
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Tallas Disponibles
                  </label>
                  <div className="grid grid-cols-3 sm:grid-cols-4 gap-2">
                    {tallasDisponibles.map((talla) => (
                      <button
                        key={talla}
                        type="button"
                        onClick={() => handleTallaChange(talla)}
                        className={`py-1.5 px-2 cursor-pointer rounded-lg border-2 font-medium transition-all text-sm ${
                          productData.tallas.includes(talla)
                            ? "bg-[#FF7700] text-white border-orange-400 "
                            : "bg-white text-gray-700 border-gray-300 hover:bg-orange-500 hover:text-white hover:border-orange-400"
                        }`}
                      >
                        {talla}
                      </button>
                    ))}
                  </div>
                  {productData.tallas.length > 0 && (
                    <p className="mt-1 text-sm text-gray-600">
                      Seleccionadas: {productData.tallas.join(", ")}
                    </p>
                  )}
                </div>
              </div>

              {/* Botones de Acción */}
              <div className="flex-shrink-0 flex flex-col sm:flex-row gap-2 mt-4 pt-3 border-t border-gray-100">
                <button
                  type="button"
                  onClick={goBack}
                  className="flex-1 px-2 py-2 cursor-pointer bg-white border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 font-medium transition-colors text-sm"
                >
                  Cancelar
                </button>
                <button
                  type="button"
                  onClick={handleSubmit}
                  className="flex-1 px-2 py-2 cursor-pointer bg-[#FF7700] text-white rounded-lg hover:bg-[#ff7700ce] font-medium transition-colors text-sm"
                >
                  Crear Producto
                </button>
              </div>
            </div>
          </div>

          {/* Sección Imagen - Responsive */}
          <div className="w-full lg:w-3/5 bg-[#AFCBD3] rounded-xl shadow-lg">
            <div className="p-3 border-b border-gray-200">
              <h2 className="text-xl font-semibold text-gray-800">
                Imagen del Producto
              </h2>
            </div>

            <div className="p-3">
              {/* Área de Upload */}
              <div
                className="border-2 border-dashed border-gray-400 rounded-xl flex flex-col items-center justify-center cursor-pointer hover:border-[#FF7700] hover:bg-orange-50 transition-colors h-[400px]"
                onDragOver={handleDragOver}
                onDrop={handleDrop}
                onClick={() => document.getElementById("imageInput").click()}
              >
                {imagePreview ? (
                  <div className="w-full h-full flex flex-col items-center justify-center">
                    <div className="flex-1 flex items-center justify-center w-full h-full overflow-hidden">
                      <img
                        src={imagePreview}
                        alt="Preview"
                        className="max-w-full max-h-full object-contain rounded-lg shadow-lg"
                        style={{ maxHeight: "380px", maxWidth: "100%" }}
                      />
                    </div>
                    <p className="mt-3 text-sm text-gray-700 flex-shrink-0">
                      Haz clic para cambiar la imagen
                    </p>
                  </div>
                ) : (
                  <div className="text-center p-8">
                    <div className="w-20 h-20 mx-auto mb-6 bg-gray-200 rounded-full flex items-center justify-center">
                      <svg
                        className="w-10 h-10 text-gray-500"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          strokeWidth={2}
                          d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z"
                        />
                      </svg>
                    </div>
                    <h3 className="text-xl font-medium text-gray-800 mb-3">
                      Sube una imagen del producto
                    </h3>
                    <p className="text-gray-700 mb-4">
                      Arrastra y suelta una imagen aquí, o haz clic para
                      seleccionar
                    </p>
                    <p className="text-sm text-gray-600">
                      PNG, JPG, GIF hasta 10MB
                    </p>
                  </div>
                )}
              </div>

              {/* Input oculto */}
              <input
                id="imageInput"
                type="file"
                accept="image/*"
                onChange={handleImageChange}
                className="hidden"
              />

              {/* Información adicional */}
              {selectedImage && (
                <div className="mt-4 p-1 bg-gray-100 rounded-lg border border-gray-300">
                  <p className="text-sm text-gray-700 mb-1">
                    <strong>Archivo:</strong> {selectedImage.name}
                  </p>
                  <p className="text-sm text-gray-700">
                    <strong>Tamaño:</strong>{" "}
                    {(selectedImage.size / 1024 / 1024).toFixed(2)} MB
                  </p>
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CrearProducto;
