const mongoose = require('mongoose')
const Schema = mongoose.Schema

const Couponschema = new Schema({
    brand: String,
    description: String,
    code: String,
    prize: Number
});

module.exports = mongoose.model('Coupon',Couponschema);