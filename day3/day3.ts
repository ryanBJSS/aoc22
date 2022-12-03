import * as fs from 'fs';
import _ from "lodash";

function itemToValue(item: string): number {
  const LOWERCASE_ASCII_OFFET: number = 97;
  const UPPERCASE_ASCII_OFFET: number = 65;

  const lowerCaseAsciiValues = Array.from(Array(26)).map((_, index) => [String.fromCharCode(index + LOWERCASE_ASCII_OFFET), index + 1]);
  const upperCaseAsciiValues = Array.from(Array(26)).map((_, index) => [String.fromCharCode(index + UPPERCASE_ASCII_OFFET), index + 27]);
  const fullList = lowerCaseAsciiValues.concat(upperCaseAsciiValues);
  return Object.fromEntries(fullList)[item]
}

class Rucksack {
  _compartmentOne: Array<string>;
  _compartmentTwo: Array<string>;
  constructor(contents: string) {
    this._compartmentOne = Array.from(contents.slice(0, contents.length / 2));
    this._compartmentTwo = Array.from(contents.slice(contents.length / 2, contents.length));
  }

  valueOfDuplicateItem(): number {
    const duplicateItem = this._compartmentOne.filter(value => this._compartmentTwo.includes(value))[0];
    return itemToValue(duplicateItem);
  }
}

class Group {
  _elfOne: Array<string>;
  _elfTwo: Array<string>;
  _elfThree: Array<string>;

  constructor(elfOne: string, elfTwo: string, elfThree: string) {
    this._elfOne = Array.from(elfOne);
    this._elfTwo = Array.from(elfTwo);
    this._elfThree = Array.from(elfThree);
  }

  valueOfDuplicateItem(): number {
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

const groupTotal = _.chunk(input, 3)
  .map((bag) => new Group(bag[0], bag[1], bag[2]).valueOfDuplicateItem())
  .reduce((total, score) => total + score, 0);

console.log(groupTotal);
