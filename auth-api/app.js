import express from 'express';

import * as db from './src/config/db/initialData.js';


const app = express();
const env = process.env;  
const PORT = env.PORT || 8080;

db.createInitialData();

app.get('/api/status', (req, res) => {
    return res.status(200).json({
        service: "Auth-api",
        status: "up",
        httpStatus: 200
    });
});

app.listen(PORT, () => {
    console.info(`Server started successful at port ${PORT}`)
});