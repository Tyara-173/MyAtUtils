from nicegui import ui
import pyperclip
import os

dirname = os.path.join(os.path.dirname(__file__), 'Utils')

def copy(fine_name):
    filename = os.path.join(dirname, fine_name + '.java')
    with open(filename) as f:
        pyperclip.copy(f.read())


with ui.card():
    ui.label('探索')
    with ui.row():
        ui.button('bit全探索', on_click=lambda: copy('bitAll'))
        ui.button('にぶたん_lower', on_click=lambda: copy('Lower_Bound'))
        ui.button('にぶたん_upper', on_click=lambda: copy('Upper_Bound'))
        ui.button('順列全列挙', on_click=lambda: copy('Permutation'))

with ui.card():
    ui.label('グラフアルゴリズム')
    with ui.row():
        ui.button('DFS', on_click=lambda: copy('DFS'))
        ui.button('BFS', on_click=lambda: copy('BFS'))
        ui.button('ダイクストラ', on_click=lambda: copy('Dijkstra'))
    with ui.row():
        ui.button('隣接マス_4', on_click=lambda: copy('neighbor4'))
        ui.button('隣接マス_8', on_click=lambda: copy('neighbor8'))

with ui.card():
    ui.label('データ構造')
    with ui.row():
        ui.button('Pair', on_click=lambda: copy('Pair'))
        ui.button('IntPair', on_click=lambda: copy('IntPair'))
        ui.button('LongPair', on_click=lambda: copy('LongPair'))
    with ui.row():
        ui.button('セグ木', on_click=lambda: copy('SegmentTree'))
        ui.button('遅延セグ木', on_click=lambda: copy('LazySegmentTree'))
        ui.button('UnionFind', on_click=lambda: copy('UnionFind'))
        ui.button('BIT', on_click=lambda: copy('BIT'))
        ui.button('Trie木', on_click=lambda: copy('Trie'))
        ui.button('BinaryTrie', on_click=lambda: copy('BinaryTrie'))


with ui.card():
    ui.label('数学系')
    with ui.row():
        ui.button('lcm', on_click=lambda: copy('LCM'))
        ui.button('lcm（配列）', on_click=lambda: copy('ArrayLCM'))
        ui.button('gcd', on_click=lambda: copy('GCD'))
        ui.button('gcd（配列）', on_click=lambda: copy('ArrayGCD'))
        ui.button('拡張gcd', on_click=lambda: copy('ExtGCD'))
    with ui.row():
        ui.button('基数変換', on_click=lambda: copy('Nary'))
        ui.button('二進数変換', on_click=lambda: copy('binary'))
    with ui.row():
        ui.button('mod逆元', on_click=lambda: copy('inv'))
        ui.button('座標圧縮', on_click=lambda: copy('compress'))
        ui.button('二次元座標圧縮', on_click=lambda: copy('compress2'))
    with ui.row():
        ui.button('エラトステネスの篩', on_click=lambda: copy('sieve'))
        ui.button('素因数分解', on_click=lambda: copy('Prime_Factorize'))



with ui.card():
    ui.label('その他')
    with ui.row():
        ui.button('一次元累積和', on_click=lambda: copy('cumulSUM'))
        ui.button('二次元累積和', on_click=lambda: copy('cumulSUM'))
        ui.button('要素数Map', on_click=lambda: copy('MapUtil'))


ui.run()
