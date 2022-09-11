interface SortableId {
    id: number
}

function sortSortableId <T extends SortableId>(
    arr: Array<T>,
    desc: boolean = false
): Array<T> {
    const sortFunction: (left: T, right: T) => number = (left, right) => {
        if (desc) {
            return left.id - right.id
        } else {
            return right.id - left.id
        }
    }

    return arr.sort(sortFunction)
}


const data = [

    { id: 2, name: 'Петя' },
    
    { id: 1, name: 'Вася' },
    
    { id: 3, name: 'Надя' },
    
];

console.log(sortSortableId(data))
console.log(sortSortableId(data, true))