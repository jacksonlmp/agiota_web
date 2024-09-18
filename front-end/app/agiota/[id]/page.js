export default function GetAgiota({params}) {
    return (
      <div className="flex min-h-screen flex-col items-center justify-between p-24">
       <h1>Informações do Agiota de id: {params.id} </h1>
      </div>
    );
  }