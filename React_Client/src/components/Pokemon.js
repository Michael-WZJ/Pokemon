
const Pokemon = () => {
  //const imagePath = "file:///f:/PokemonPics/001.jpg";
  const imagePath = "PokemonPics/9970(3)  睡眠扭蛋3 (2).jpg";
  //const imagePath = "PokemonPics/001.jpg";
  const name = "妙蛙种子";
  const id = "001";
  const gen = "OP";
  const nameJp = "フシギダネ";
  const nameEng = "Bulbasaur";


  return (
    <div className="row">
      <div className="col-sm">
        <figure>
          <img src={`${imagePath}`} width="100%"
               alt={name} title={name}/>
        </figure>
      </div>

      <div className="col">
        <div>
          <label>
            <strong>编号</strong>
          </label> {` ${id}`}
        </div>
        <div>
          <label>
            <strong>名称</strong>
          </label> {` ${name}`}
        </div>
          <div>
            <label>
              <strong>属性</strong>
            </label>
          </div>
          <div>
            <label>
              <strong>世代</strong>
            </label> {` ${gen}`}
          </div>

          <div>
            <label>
              <strong>日文</strong>
            </label> {` ${nameJp}`}
          </div>
          <div>
            <label>
              <strong>英文</strong>
            </label> {` ${nameEng}`}
          </div>
      </div>
    </div>
  );
}

export default Pokemon;