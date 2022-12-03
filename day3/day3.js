"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const fs = __importStar(require("fs"));
const lodash_1 = __importDefault(require("lodash"));
function itemToValue(item) {
    const LOWERCASE_ASCII_OFFET = 97;
    const UPPERCASE_ASCII_OFFET = 65;
    const lowerCaseAsciiValues = Array.from(Array(26)).map((_, index) => [String.fromCharCode(index + LOWERCASE_ASCII_OFFET), index + 1]);
    const upperCaseAsciiValues = Array.from(Array(26)).map((_, index) => [String.fromCharCode(index + UPPERCASE_ASCII_OFFET), index + 27]);
    const fullList = lowerCaseAsciiValues.concat(upperCaseAsciiValues);
    return Object.fromEntries(fullList)[item];
}
class Rucksack {
    constructor(contents) {
        this._compartmentOne = Array.from(contents.slice(0, contents.length / 2));
        this._compartmentTwo = Array.from(contents.slice(contents.length / 2, contents.length));
    }
    valueOfDuplicateItem() {
        const duplicateItem = this._compartmentOne.filter(value => this._compartmentTwo.includes(value))[0];
        return itemToValue(duplicateItem);
    }
}
class Group {
    constructor(elfOne, elfTwo, elfThree) {
        this._elfOne = Array.from(elfOne);
        this._elfTwo = Array.from(elfTwo);
        this._elfThree = Array.from(elfThree);
    }
    valueOfDuplicateItem() {
        const duplicateItem = this._elfOne
            .filter(value => this._elfTwo.includes(value))
            .filter(value => this._elfThree.includes(value))[0];
        return itemToValue(duplicateItem);
    }
}
const input = fs.readFileSync('input.txt', 'utf8').split("\n");
const total = input
    .map((bag) => new Rucksack(bag).valueOfDuplicateItem())
    .reduce((total, score) => total + score, 0);
console.log(total);
const groupTotal = lodash_1.default.chunk(input, 3)
    .map((bag) => new Group(bag[0], bag[1], bag[2]).valueOfDuplicateItem())
    .reduce((total, score) => total + score, 0);
console.log(groupTotal);
