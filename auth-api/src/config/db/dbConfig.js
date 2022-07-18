import Sequelize from "sequelize";

const sequelize = new Sequelize("auth-db", "admin", "123456", {
  host: "localhost",
  dialect: "postgres",
  quoteIdentifiers: false,  
  define: {
    syncOnAssociation: true,
    timestamps: false,
    underscored: true,
    underscoredAll: true,
    freezeTableName: true,
  },
});

sequelize
  .authenticate()
  .then(() => {
    console.info("connection has been stablished!");
  })
  .catch((err) => {
    console.error("error connect to the database");
    console.error("error message => ", err.message);
  });

export default sequelize;
